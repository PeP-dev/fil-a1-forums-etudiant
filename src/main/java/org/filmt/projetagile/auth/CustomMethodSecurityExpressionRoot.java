package org.filmt.projetagile.auth;

import javax.servlet.http.HttpServletRequest;

import org.filmt.projetagile.exception.GroupNotFoundException;
import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.posts.service.PostService;
import org.filmt.projetagile.roles.model.Role;
import org.filmt.projetagile.roles.service.RoleService;
import org.filmt.projetagile.school.model.School;
import org.filmt.projetagile.user.model.UserModel;
import org.filmt.projetagile.user.service.UserService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private RoleService<Group> roleGroupService;

    private RoleService<School> roleSchoolService;

    private PostService postService;

    private GroupService groupService;

    private UserService userService;

    private HttpServletRequest request;

    private Object filterObject;

    private Object returnObject;

    private Object target;

    public CustomMethodSecurityExpressionRoot(Authentication authentication,
        RoleService<Group> roleGroupService,
        RoleService<School> roleSchoolService,
        PostService postService,
        GroupService groupService,
        UserService userService){
        super(authentication);
        this.roleGroupService = roleGroupService;
        this.roleSchoolService = roleSchoolService;
        this.postService = postService;
        this.groupService = groupService;
        this.userService = userService;
    }

    public boolean groupPrivilegesHigherThan(final String groupId, final String minRole) {
        String userName = ((UserDetails) getAuthentication().getPrincipal()).getUsername();
        return isGlobalAdmin(userName) || roleGroupService.getRoleForEntity(userName, groupId).map(role->role.isAtLeast(Role.valueOf(minRole))).orElse(false);
    }

    public boolean schoolPrivilegesHigherThan(final String groupId, final String minRole) {
        String userName = ((UserDetails) getAuthentication().getPrincipal()).getUsername();
        String schoolId = groupService.getGroupById(groupId).map(Group::getSchoolId).orElseThrow(()->GroupNotFoundException.genericById(groupId));
        return isGlobalAdmin(userName) || roleSchoolService.getRoleForEntity(userName, schoolId).map(role->role.isAtLeast(Role.valueOf(minRole))).orElse(false);
    }

    public boolean isAuthor(final String postId) {
        String userName = ((UserDetails) getAuthentication().getPrincipal()).getUsername();
        return isGlobalAdmin(userName) || postService.getPostById(postId).map(post->post.getUserName().equals(userName)).orElse(false);
    }

    public boolean isGlobalAdmin(final String username) {
        return userService.getUserByUsername(username).map(UserModel::isGlobalAdmin).orElse(false);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }
}
