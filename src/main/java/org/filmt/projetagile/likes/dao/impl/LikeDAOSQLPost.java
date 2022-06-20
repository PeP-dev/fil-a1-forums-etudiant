package org.filmt.projetagile.likes.dao.impl;

import org.filmt.projetagile.likes.dao.LikeDAOSQL;
import org.filmt.projetagile.posts.model.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LikeDAOSQLPost extends LikeDAOSQL<Post> {

    public LikeDAOSQLPost(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public String getDaoTableName() {
        return "LIKE_POST";
    }

    @Override
    public String getContentColumnName() {
        return "POST_ID";
    }

    @Override
    public String getUserColumnName() {
        return "USER_ID";
    }

    @Override
    protected RowMapper<Post> getRowMapper() {
        return null;
    }
}
