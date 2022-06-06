package org.filmt.projetagile.auth.config;

import org.filmt.projetagile.auth.service.UserService;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.posts.service.PostService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import lombok.AllArgsConstructor;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final UserService userService;

    private final PostService postService;

    private final GroupService groupService;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new CustomMethodSecurityExpressionHandler(new AuthenticationTrustResolverImpl(), userService, postService, groupService);
    }
}