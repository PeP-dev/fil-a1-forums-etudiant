package org.filmt.projetagile.likes.service.impl;

import org.filmt.projetagile.exception.PublicException;
import org.filmt.projetagile.exception.ReplyNotFoundException;
import org.filmt.projetagile.likes.dao.LikeDAO;
import org.filmt.projetagile.likes.service.LikeService;
import org.filmt.projetagile.posts.service.PostService;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.ReplyService;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceReply extends LikeService<Reply> {

    ReplyService replyService;

    public LikeServiceReply(final LikeDAO<Reply> likeDao, final ReplyService replyService) {
        super(likeDao);
        this.replyService = replyService;
    }

    @Override
    protected void throwIfMissingContent(final String contentId) throws PublicException {
        if (replyService.getReplyById(contentId).isEmpty()) {
            throw new ReplyNotFoundException(String.format("Couldn't find a post with matching identifier '%s'", contentId));
        }
    }
}
