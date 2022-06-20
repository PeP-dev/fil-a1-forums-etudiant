package org.filmt.projetagile.replies.controller;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.impl.PostServiceImpl;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.impl.ReplyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{postId}")
    public List<Reply> getRepliesByPostId(@PathVariable String postId) {
        return replyService.getRepliesByPostId(postId);
    }

    @GetMapping(value = "/comments/{replyId}")
    public List<Reply> getCommentsByReplyId(@PathVariable String replyId) {
        return replyService.getCommentsByReplyId(replyId);
    }

}
