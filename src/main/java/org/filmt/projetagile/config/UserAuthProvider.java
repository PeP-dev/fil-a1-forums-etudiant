package org.filmt.projetagile.config;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.auth.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Primary
@AllArgsConstructor
public class UserAuthProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails user = null;
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            user = userService.login((String) authentication.getPrincipal(), (String) authentication.getCredentials());

        } else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            user = userService.loginWithToken((String) authentication.getPrincipal());
        }
        if (user == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
