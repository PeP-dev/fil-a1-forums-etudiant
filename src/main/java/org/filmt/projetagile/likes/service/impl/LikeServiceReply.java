package org.filmt.projetagile.likes.service.impl;

import org.filmt.projetagile.exception.PublicException;
import org.filmt.projetagile.exception.ReplyNotFoundException;
import org.filmt.projetagile.likes.dao.LikeDAO;
import org.filmt.projetagile.likes.service.LikeService;
import org.filmt.projetagile.posts.service.PostService;
import org.filmt.projetagile.replies.model.Reply;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceReply extends LikeService<Reply> {

    PostService postService;

    public LikeServiceReply(final LikeDAO<Reply> likeDao, final PostService postService) {
        super(likeDao);
        this.postService = postService;
    }

    @Override
    protected void throwIfMissingContent(final String contentId) throws PublicException {
        if (postService.getPostById(contentId).isEmpty()) {
            throw new ReplyNotFoundException(String.format("Couldn't find a post with matching identifier '%s'", contentId));
        }
    }
}
