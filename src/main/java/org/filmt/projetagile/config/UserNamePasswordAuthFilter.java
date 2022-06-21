package org.filmt.projetagile.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.filmt.projetagile.auth.model.LoginCredentials;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserNamePasswordAuthFilter extends OncePerRequestFilter {

    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (SecurityConfig.LOGIN_URL.equals(request.getServletPath())
                && HttpMethod.POST.matches((request.getMethod()))) {
            LoginCredentials credentials = MAPPER.readValue(request.getInputStream(), LoginCredentials.class);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()
                    )
            );
        }
        filterChain.doFilter(request, response);
    }
}
