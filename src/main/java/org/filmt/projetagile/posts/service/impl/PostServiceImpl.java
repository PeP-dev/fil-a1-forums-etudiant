package org.filmt.projetagile.posts.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.filmt.projetagile.exception.CategoryNotFoundException;
import org.filmt.projetagile.exception.GroupNotFoundException;
import org.filmt.projetagile.exception.UserNotFoundException;
import org.filmt.projetagile.posts.dao.PostDAO;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.PostService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Primary
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDao;

    @Override
    public Optional<Post> getPostById(final String postId) {
        return Optional.empty();
    }

    @Override
    public List<Post> getPostsByGroupId(final String groupId) {
        if (postDao.getPostsByGroupId(groupId).isEmpty()) {
            throw GroupNotFoundException.genericById(groupId);
        }
        return postDao.getPostsByGroupId(groupId);
    }

    @Override
    public List<Post> getPostByCategory(final String groupId, final String categoryId) {
        if (postDao.getPostByCategory(groupId, categoryId).isEmpty()) {
            throw CategoryNotFoundException.genericById(groupId);
        }
        return postDao.getPostByCategory(groupId, categoryId);
    }

    @Override
    public List<Post> getPostsBySchoolIdAndTitle(final String schoolId, final String title) {
        return postDao.getPostsBySchoolIdAndTitle(schoolId, title);
    }

    @Override
    public List<Post> getPostsByGroupIdAndTitle(final String groupId, final String title) {
        return postDao.getPostsByGroupIdAndTitle(groupId, title);
    }

    @Override
    public List<Post> getPostsByUserId(String userId)  {
        if (postDao.getPostsByUserId(userId).isEmpty()) {
            throw new UserNotFoundException("Cannot find user") ;
        }
        return postDao.getPostsByUserId(userId) ;
    }

    @Override
    public Post create(final Post post) {
        post.setId(UUID.randomUUID().toString());
        postDao.create(post);
        return post ;
    }

    @Override
    public Post update(final Post post) {
        return postDao.update(post);
    }

    @Override
    public void delete(final String id) {
        postDao.delete(id);
    }
}
