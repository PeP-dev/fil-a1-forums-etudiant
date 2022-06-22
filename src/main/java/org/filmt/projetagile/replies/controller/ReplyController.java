package org.filmt.projetagile.replies.controller;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.replies.service.impl.ReplyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("api/replies")
public class ReplyController {

    private final ReplyServiceImpl replyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Reply> create(@RequestBody Reply reply) {
        return ResponseEntity.ok(replyService.create(reply));
    }

    @GetMapping(value = "/{replyId}")
    public ResponseEntity<Optional<Reply>> getReplyById(@PathVariable String replyId) {
        return ResponseEntity.ok(replyService.getReplyById(replyId));
    }

    @GetMapping(value = "",params = "postId" )
    public ResponseEntity<List<Reply>> getRepliesByPostId(@RequestParam final String postId) {
        return ResponseEntity.ok(replyService.getRepliesByPostId(postId));
    }

    @GetMapping(value = "/{replyId}/comments")
    public ResponseEntity<List<Reply>> getCommentsByReplyId(@PathVariable String replyId) {
        return ResponseEntity.ok(replyService.getCommentsByReplyId(replyId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ResponseEntity<Reply> update(@RequestBody Reply reply) {
        return ResponseEntity.ok(replyService.update(reply));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{replyId}")
    public ResponseEntity<Void> delete(@PathVariable String replyId) {
        replyService.delete(replyId);
        return ResponseEntity.noContent().build();
    }



}
