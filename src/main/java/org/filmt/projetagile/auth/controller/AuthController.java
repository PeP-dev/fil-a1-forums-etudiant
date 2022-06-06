package org.filmt.projetagile.auth.controller;

import java.util.Collections;
import java.util.Map;

import org.filmt.projetagile.auth.model.LoginCredentials;
import org.filmt.projetagile.auth.model.UserModel;
import org.filmt.projetagile.auth.service.UserService;
import org.filmt.projetagile.auth.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;
    private JWTUtil jwtUtil;

    private AuthenticationManager authManager;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String,Object> registerHandler(@RequestBody LoginCredentials user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        String token = jwtUtil.generateToken(user.getUserName());
        return Collections.singletonMap("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UserDetails details = userService.loadUserByUsername(body.getUserName());
            if(!passwordEncoder.matches(body.getPassword(), details.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }
            UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authInputToken);
            String token = jwtUtil.generateToken(body.getUserName());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }
}
