package org.filmt.projetagile.replies.controller;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.exception.NotFoundException;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.impl.ReplyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            return replyService.getRepliesByPostId(postId);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Post Not Found", ex);
        }
    }

    @GetMapping(value = "/comments/{replyId}")
    public List<Reply> getCommentsByReplyId(@PathVariable String replyId) {
        try {
            return replyService.getCommentsByReplyId(replyId);
        } catch (NotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Comments for this reply not found", ex);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Reply update(@RequestBody Reply reply) {
        return replyService.update(reply);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{replyId}")
    public void delete(@PathVariable String replyId) {
        replyService.delete(replyId);
    }

}
