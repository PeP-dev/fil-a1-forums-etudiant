package org.filmt.projetagile.posts.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.filmt.projetagile.exception.*;
import org.filmt.projetagile.groups.service.GroupService;
import org.filmt.projetagile.posts.dao.PostDAO;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.PostService;
import org.filmt.projetagile.school.service.SchoolService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Primary
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDao;

    GroupService groupService ;
    SchoolService schoolService;

    @Override
    public Optional<Post> getPostById(final String postId) {
        return postDao.getPostById(postId);
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
        return postDao.getPostByCategory(groupId, categoryId);
    }

    @Override
    public List<Post> getPostsBySchoolIdAndTitle(final String schoolId, final String title) {
        if (schoolService.getSchoolById(schoolId).isEmpty()) {
            throw SchoolNotFoundException.genericById(schoolId);
        }
        return postDao.getPostsBySchoolIdAndTitle(schoolId, title);
    }

    @Override
    public List<Post> getPostsByGroupIdAndTitle(final String groupId, final String title) {
        if (groupService.getGroupById(groupId).isEmpty()) {
            throw GroupNotFoundException.genericById(groupId);
        }
        return postDao.getPostsByGroupIdAndTitle(groupId, title);
    }

    @Override
    public List<Post> getPostsByUserId(String username)  {
        if (postDao.getPostsByUserId(username).isEmpty()) {
            throw new UserNotFoundException("Cannot find user") ;
        }
        return postDao.getPostsByUserId(username) ;
    }

    @Override
    public Post create(final Post post) {
        post.setId(UUID.randomUUID().toString());
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if(groupService.getGroupById(post.getGroupId()).isEmpty()) {
            throw GroupNotFoundException.genericById(post.getGroupId());
        }
        postDao.create(post);
        return post ;
    }

    @Override
    public Post update(final Post post) {
        if (groupService.getGroupById(post.getGroupId()).isEmpty()) {
            throw GroupNotFoundException.genericById(post.getGroupId());
        }
        if (getPostById(post.getId()).isEmpty()) {
            throw PostNotFoundException.genericById(post.getId());
        }
        return postDao.update(post);
    }

    @Override
    public void delete(final String id) {
        if(getPostById(id).isPresent()) {
            postDao.delete(id);
        } else {
            throw PostNotFoundException.genericById(id);
        }
    }
}
