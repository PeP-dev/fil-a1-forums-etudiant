package org.filmt.projetagile.likes.controller;

import java.util.List;

import org.filmt.projetagile.likes.service.LikeService;
import org.filmt.projetagile.user.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbstractLikeController<T> {

    LikeService<T> likeService;

    @GetMapping(value = "", params = "userId")
    public ResponseEntity<List<T>> getUserLikes(@RequestParam String userId) {
        return ResponseEntity.ok(likeService.getLikedContentByUser(userId));
    }

    @GetMapping(value = "/amount", params = "contentId")
    public ResponseEntity<Integer> getContentLikesAmount(@RequestParam String contentId) {
        return ResponseEntity.ok(likeService.getLikeAmount(contentId));
    }

    @GetMapping(value = "", params = "contentId")
    public ResponseEntity<List<UserModel>> getContentLikes(@RequestParam String contentId) {
        return ResponseEntity.ok(likeService.getContentLikes(contentId));
    }

    @DeleteMapping("")
    @PreAuthorize("authenticated")
    public ResponseEntity<List<T>> deleteLike(@RequestParam String userId, @RequestParam String contentId) {
        likeService.removeLike(userId, contentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    @PreAuthorize("authenticated")
    public ResponseEntity<List<T>> postLike(@RequestParam String userId, @RequestParam String contentId) {
        likeService.addLike(userId, contentId);
        return ResponseEntity.noContent().build();
    }
}
