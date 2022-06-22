package org.filmt.projetagile.likes.dao.impl;

import org.filmt.projetagile.likes.dao.LikeDAOSQL;
import org.filmt.projetagile.posts.model.Post;
import org.filmt.projetagile.replies.model.Reply;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class LikeDAOSQLReply extends LikeDAOSQL<Reply> {

    protected LikeDAOSQLReply(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public String getDaoTableName() {
        return "REDDIMT_LIKE_REPLY";
    }

    @Override
    public String getContentTableName() {
        return "REDDIMT_REPLY";
    }

    @Override
    public String getContentPk() {
        return "ID";
    }

    @Override
    public String getContentColumnName() {
        return "ID_REPLY";
    }

    @Override
    public String getUserColumnName() {
        return "USER_NAME";
    }
    @Override
    protected RowMapper<Reply> getContentRowMapper() {
        return null;
    }
}
