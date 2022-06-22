package org.filmt.projetagile.likes.controller;

import org.filmt.projetagile.posts.model.Post;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/likes/posts")
public class LikeControllerPost extends AbstractLikeController<Post> {}
