package org.filmt.projetagile.replies.controller;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.impl.PostServiceImpl;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.impl.ReplyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/replies")
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Reply create(@RequestBody Reply reply) {
        return replyService.create(reply);
    }
}
