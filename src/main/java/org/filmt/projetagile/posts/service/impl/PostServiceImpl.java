package org.filmt.projetagile.posts.service.impl;

import java.util.List;

import org.filmt.projetagile.posts.dao.PostDAO;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.PostService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Primary
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDao;

    @Override
    public Post getPostById(final String postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByGroupId(final String groupId) {
        return postDao.getPostsByGroupId(groupId);
    }

    @Override
    public List<Post> getPostByCategory(final String groupId, final String categoryId) {
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
    public Post create(final Post post) {
        return postDao.create(post);
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
