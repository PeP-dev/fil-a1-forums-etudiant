package org.filmt.projetagile.config;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.filmt.projetagile.auth.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CookieAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private AuthService authService;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authentication) throws IOException, ServletException {
        createCookie(authentication, response);
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
            createCookie(authentication, response);
    }

    private void createCookie(Authentication auth, HttpServletResponse response) {
        Cookie cookie = new Cookie(CookieAuthenticationFilter.COOKIE_NAME,
            authService.createToken((UserDetails) auth.getPrincipal()));
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
