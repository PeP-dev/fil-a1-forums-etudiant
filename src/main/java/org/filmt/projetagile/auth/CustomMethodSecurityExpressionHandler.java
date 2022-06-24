package org.filmt.projetagile.auth;

import org.aopalliance.intercept.MethodInvocation;
import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.posts.service.PostService;
import org.filmt.projetagile.roles.service.RoleService;
import org.filmt.projetagile.roles.service.impl.RoleServiceGroup;
import org.filmt.projetagile.roles.service.impl.RoleServiceSchool;
import org.filmt.projetagile.school.model.School;
import org.filmt.projetagile.user.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private ApplicationContext applicationContext;
    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {

        CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication,
            applicationContext.getBean(RoleServiceGroup.class),
            applicationContext.getBean(RoleServiceSchool.class),
            applicationContext.getBean(PostService.class),
            applicationContext.getBean(GroupService.class),
            applicationContext.getBean(UserService.class));
        root.setTrustResolver(this.trustResolver);
        root.setPermissionEvaluator(getPermissionEvaluator());

        return root;
    }

    //This setter method will be called from the config class
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        this.applicationContext=applicationContext;
    }
}