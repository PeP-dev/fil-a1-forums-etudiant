package org.filmt.projetagile.likes.service.impl;

import org.filmt.projetagile.exception.PostNotFoundException;
import org.filmt.projetagile.exception.PublicException;
import org.filmt.projetagile.likes.dao.LikeDAO;
import org.filmt.projetagile.likes.service.LikeService;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class LikeServicePost extends LikeService<Post> {

    PostService postService;

    public LikeServicePost(final LikeDAO<Post> likeDao, final PostService postService) {
        super(likeDao);
        this.postService = postService;
    }

    @Override
    protected void throwIfMissingContent(final String contentId) throws PublicException {
        if (postService.getPostById(contentId).isEmpty()) {
            throw new PostNotFoundException(String.format("Couldn't find a post with matching identifier '%s'", contentId));
        }
    }
}
