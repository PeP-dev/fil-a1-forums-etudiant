package org.filmt.projetagile.auth.config;

import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.model.UserModel;
import org.filmt.projetagile.auth.service.UserService;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.PostService;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;

    private Object returnObject;

    private Object target;

    private UserService userService;

    private PostService postService;

    private GroupService groupService;

    public CustomMethodSecurityExpressionRoot(Authentication a, UserService userService, PostService postService, GroupService groupService) {
        super(a);
        this.userService = userService;
        this.postService = postService;
        this.groupService = groupService;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    void setThis(Object target) {
        this.target = target;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    /**
     * Vérifie que l'utilisateur qui sélectionne la publication est administrateur du groupe dans lequel se situe la publication
     *
     * @param postId l'identifiant de la publication
     * @return vrai si l'utilisateur est au minimum administrateur du groupe, faux sinon
     */
    public boolean isGroupAdmin(String postId) {
        log.info("Verifying group admin");
        Post post = postService.getPostById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        UserModel user = (UserModel) this.getPrincipal();
        return userService.getRoleByUserNameAndGroupId(user.getUsername(), post.getGroupId()).higherOrEqualTo(GroupRole.ADMIN);
    }

    /**
     * Vérifie qu'un utilisateur est le créateur de la publication
     *
     * @param postId l'identifiant de la publication
     * @return vrai si l'utilisateur est le créateur de la publication, faux sinon
     */
    public boolean isCreator(String postId) {
        log.info("Verifying post creator");
        Post post = postService.getPostById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        UserModel user = (UserModel) this.getPrincipal();
        return post.getUserName().equals(user.getUsername());
    }
}