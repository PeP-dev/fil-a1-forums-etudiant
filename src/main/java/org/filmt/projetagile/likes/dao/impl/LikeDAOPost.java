package org.filmt.projetagile.likes.dao.impl;

import org.filmt.projetagile.likes.dao.LikeDAO;
import org.filmt.projetagile.posts.model.Post;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDAOPost implements LikeDAO<Post> {

    @Override
    public String getDaoTableName() {
        return "LIKE_POST";
    }
}
