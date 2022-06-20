package org.filmt.projetagile.config;

import lombok.AllArgsConstructor;

import org.filmt.projetagile.auth.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout().deleteCookies(CookieAuthenticationFilter.COOKIE_NAME)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    private UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        var filter = new UsernamePasswordAuthenticationFilter(authenticationManagerBean());
        filter.setFilterProcessesUrl(LOGIN_URL);
        filter.setAuthenticationSuccessHandler(successHandler());
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
