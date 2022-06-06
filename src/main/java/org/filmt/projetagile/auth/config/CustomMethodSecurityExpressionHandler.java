package org.filmt.projetagile.auth.config;

import org.aopalliance.intercept.MethodInvocation;
import org.filmt.projetagile.auth.service.UserService;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.posts.service.PostService;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomMethodSecurityExpressionHandler
    extends DefaultMethodSecurityExpressionHandler {
    private final AuthenticationTrustResolver trustResolver;

    private final UserService userService;

    private final PostService postService;

    private final GroupService groupService;
    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
        Authentication authentication, MethodInvocation invocation) {
        CustomMethodSecurityExpressionRoot root =
            new CustomMethodSecurityExpressionRoot(authentication, userService, postService, groupService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}