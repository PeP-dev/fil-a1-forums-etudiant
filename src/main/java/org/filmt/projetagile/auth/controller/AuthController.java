package org.filmt.projetagile.auth.controller;

import lombok.AllArgsConstructor;

import org.filmt.projetagile.auth.model.LoginCredentials;
import org.filmt.projetagile.auth.model.RegisterCredentials;
import org.filmt.projetagile.auth.service.AuthService;
import org.filmt.projetagile.config.CookieAuthenticationFilter;
import org.filmt.projetagile.roles.model.Role;
import org.filmt.projetagile.roles.service.RoleService;
import org.filmt.projetagile.school.model.School;
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

    private RoleService<School> roleService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterCredentials user) {
        userService.registerUser(user);
        if (user.getSchoolId() != null) {
            roleService.changeRole(user.getUsername(), user.getSchoolId(), Role.ETUDIANT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0])).filter(cookie -> CookieAuthenticationFilter.COOKIE_NAME.equals(cookie.getName())).findFirst();
        authCookie.ifPresent(cookie -> cookie.setMaxAge(0));
        return ResponseEntity.noContent().build();
    }
}
