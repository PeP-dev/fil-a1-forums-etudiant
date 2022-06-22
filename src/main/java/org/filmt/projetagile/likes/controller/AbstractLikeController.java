package org.filmt.projetagile.likes.controller;

import java.util.List;

import org.filmt.projetagile.likes.Like;
import org.filmt.projetagile.likes.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class AbstractLikeController<T> {

    LikeService<T> likeService;

    @GetMapping(value = "", params = "userId")
    public ResponseEntity<List<Like<T>>> getUserLikes(@RequestParam String userId) {
        return ResponseEntity.ok(likeService.getLikedContentByUser(userId));
    }

    @GetMapping(value = "", params = "contentId")
    public ResponseEntity<Integer> getContentLikes(@RequestParam String contentId) {
        return ResponseEntity.ok(likeService.getLikeAmount(contentId));
    }

    @DeleteMapping("")
    public ResponseEntity<List<T>> deleteLike(@RequestParam String userId, @RequestParam String contentId) {
        likeService.removeLike(userId, contentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<List<T>> postLike(@RequestParam String userId, @RequestParam String contentId) {
        likeService.addLike(userId, contentId);
        return ResponseEntity.noContent().build();
    }
}
