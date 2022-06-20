package org.filmt.projetagile.likes.service.impl;

import org.filmt.projetagile.likes.dao.LikeDAO;
import org.filmt.projetagile.likes.service.LikeService;
import org.filmt.projetagile.posts.model.Post;
import org.springframework.stereotype.Service;

@Service
public class LikeServicePost extends LikeService<Post> {

    public LikeServicePost(final LikeDAO<Post> likeDao) {
        super(likeDao);
    }
}
