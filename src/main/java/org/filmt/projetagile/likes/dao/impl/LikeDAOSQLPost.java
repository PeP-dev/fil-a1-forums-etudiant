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
        return "REDDIMT_LIKE_POST";
    }

    @Override
    public String getContentTableName() {
        return "REDDIMT_POST";
    }

    @Override
    public String getContentPk() {
        return "ID";
    }

    @Override
    public String getContentColumnName() {
        return "ID_POST";
    }

    @Override
    public String getUserColumnName() {
        return "USER_NAME";
    }

    @Override
    protected RowMapper<Post> getContentRowMapper() {
        return ((rs, rowNum) -> new Post(rs.getString("id"),
            rs.getString("id_group"),
            rs.getString("title"),
            rs.getString("post_content"),
            rs.getString("id_category"),
            rs.getString("user_name"),
            rs.getTimestamp("CREATED_AT")));
    }
}
