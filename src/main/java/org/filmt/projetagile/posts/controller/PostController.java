package org.filmt.projetagile.posts.controller;

import java.util.List;

import org.filmt.projetagile.exception.PostNotFoundException;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.impl.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostServiceImpl postService;

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPosts(@PathVariable String postId) {
        return ResponseEntity.ok(postService.getPostById(postId).orElseThrow(()-> PostNotFoundException.genericById(postId)));
    }

    @GetMapping(value = "", params = "groupId")
    public ResponseEntity<List<Post>> getPostByGroupId(@RequestParam String groupId) {
        return ResponseEntity.ok(postService.getPostsByGroupId(groupId));
    }

    @GetMapping(value = "/{groupId}", params = "categoryId")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable String groupId, @RequestParam String categoryId) {
        return ResponseEntity.ok(postService.getPostByCategory(groupId, categoryId));
    }

    @GetMapping(value = "",params = "username")
    public ResponseEntity<List<Post>> getPostsByUserId(@RequestParam String username) {
        return ResponseEntity.ok(postService.getPostsByUserId(username));
    }


    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        return ResponseEntity.ok(postService.create(post));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ResponseEntity<Post> update(@RequestBody Post post) {
        return ResponseEntity.ok(postService.update(post));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable String postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }
}
