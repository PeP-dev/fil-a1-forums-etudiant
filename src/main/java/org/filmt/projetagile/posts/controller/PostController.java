package org.filmt.projetagile.posts.controller;

import java.util.List;
import java.util.UUID;

import org.filmt.projetagile.exception.GroupNotFoundException;
import org.filmt.projetagile.posts.dao.impl.PostDAOSQL;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.posts.service.PostService;
import org.filmt.projetagile.posts.service.impl.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private final PostServiceImpl postService;

    @GetMapping("/{groupId}")
    public List<Post> getPosts(@PathVariable String groupId) {
        try {
            return postService.getPostsByGroupId(groupId) ;
        } catch (GroupNotFoundException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Group Not Found", ex);
        }
    }

    @GetMapping(value = "/{groupId}", params = "categoryId")
    public List<Post> getPostsByCategory(@PathVariable String groupId, @RequestParam String categoryId) {
        return postService.getPostByCategory(groupId, categoryId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Post create(@RequestBody Post post) {
        UUID id = UUID.randomUUID();
        post.setId(id.toString());
        return postService.create(post);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Post update(@RequestBody Post post) {
        return postService.update(post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{postId}")
    public void delete(@PathVariable String post) {
        postService.delete(post);
    }
}
