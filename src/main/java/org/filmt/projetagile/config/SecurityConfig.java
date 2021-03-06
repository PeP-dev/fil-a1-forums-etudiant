package org.filmt.projetagile.config;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.filmt.projetagile.auth.service.AuthService;
import org.filmt.projetagile.exception.ApiError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

/**
 * @see <a href="https://medium.com/geekculture/implementing-json-web-token-jwt-authentication-using-spring-security-detailed-walkthrough-1ac480a8d970">JWT Auth turorial</a>
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_URL = "/api/auth/login";

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    private UserAuthProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
            .authenticationEntryPoint(userAuthenticationEntryPoint)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authenticationFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new CookieAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .csrf()
            .disable()
            .cors()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .logout()
            .deleteCookies(CookieAuthenticationFilter.COOKIE_NAME)
            .and()
            .authorizeRequests()
            .anyRequest()
            .permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    private UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        var filter = new UsernamePasswordAuthenticationFilter(authenticationManagerBean());
        filter.setFilterProcessesUrl(LOGIN_URL);
        filter.setAuthenticationSuccessHandler(successHandler());
        filter.setAuthenticationFailureHandler((request,httpServletResponse,ex)->
            {
                ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getContextPath());
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                OutputStream out = httpServletResponse.getOutputStream();
                ObjectMapper mapper = new ObjectMapper();
                mapper.findAndRegisterModules();
                mapper.writerWithDefaultPrettyPrinter().writeValue(out, error);
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                out.flush();
            });
        return filter;
    }

    @Bean
    public CookieAuthenticationSuccessHandler successHandler() {
        return new CookieAuthenticationSuccessHandler(userService());
    }

    @Bean
    public AuthService userService() {
        return getApplicationContext().getBean(AuthService.class);
    }
}
