package org.filmt.projetagile.auth.service.impl;

import org.filmt.projetagile.auth.dao.AuthenticationDAO;
import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.model.LoginCredentials;
import org.filmt.projetagile.user.model.UserModel;
import org.filmt.projetagile.auth.service.AuthService;
import org.filmt.projetagile.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Service
@Primary
public class AuthServiceImpl implements AuthService {

    private final AuthenticationDAO authenticationDAO;

    @Value("${auth_cookie_hmac-key}")
    private String secretKey;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationDAO authenticationDAO, PasswordEncoder passwordEncoder) {
        this.authenticationDAO = authenticationDAO;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(final String username) {
        return authenticationDAO.loadByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Couldn't find user with matching username : '%s'", username)));
    }

    @Override
    public UserDetails login(final String userName, String password) {
        UserDetails details = loadUserByUsername(userName);
        if (!passwordEncoder.matches(password, details.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        return details;
    }

    @Override
    public UserDetails loginWithToken(String token) {
        String[] tokenInfo = token.split("&");
        String userName = tokenInfo[0];
        String hmac = tokenInfo[1];

        UserDetails user = loadUserByUsername(userName);

        if (!hmac.equals(calculateHmac(userName))) {
            throw new RuntimeException("Invalid Cookie Value");
        }

        return user;
    }

    private String calculateHmac(String userName) {
        byte[] secretKeyBytes = Objects.requireNonNull(secretKey).getBytes(StandardCharsets.UTF_8);
        byte[] valueBytes = Objects.requireNonNull(userName).getBytes(StandardCharsets.UTF_8);

        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA512");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(valueBytes);
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    public String createToken(UserDetails userDetails) {
        return userDetails.getUsername() + "&" + calculateHmac(userDetails.getUsername());
    }

    @Override
    public GroupRole getRoleByUserNameAndGroupId(final String userName, final String groupId) {
        return authenticationDAO.getRoleByUserNameAndGroupId(userName, groupId);
    }

    @Override
    public UserDetails registerUser(final LoginCredentials credentials) {
        String encodedPass = passwordEncoder.encode(credentials.getPassword());
        UserModel model = new UserModel(credentials.getUsername(), encodedPass);
        authenticationDAO.registerUser(model);
        return model;
    }
}
