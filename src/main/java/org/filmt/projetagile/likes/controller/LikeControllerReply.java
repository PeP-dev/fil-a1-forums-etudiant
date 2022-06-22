package org.filmt.projetagile.likes.controller;

import org.filmt.projetagile.likes.service.LikeService;
import org.filmt.projetagile.replies.model.Reply;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/likes/reply")
public class LikeControllerReply extends AbstractLikeController<Reply> {

    public LikeControllerReply(final LikeService<Reply> likeService) {
        super(likeService);
    }
}
