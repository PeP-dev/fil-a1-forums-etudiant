package org.filmt.projetagile.auth.controller;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.auth.model.LoginCredentials;
import org.filmt.projetagile.auth.service.AuthService;
import org.filmt.projetagile.config.CookieAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody LoginCredentials user) {
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse response) {
        if (userDetails == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userDetails);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> CookieAuthenticationFilter.COOKIE_NAME.equals(cookie.getName()))
                .findFirst();

        authCookie.ifPresent(cookie->cookie.setMaxAge(0));

        return ResponseEntity.noContent().build();
    }
}
