package org.filmt.projetagile.replies.dao.impl;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.common.jdbc.mapper.ReplyMapper;
import org.filmt.projetagile.replies.dao.ReplyDAO;
import org.filmt.projetagile.replies.model.Reply;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReplyDAOSQL extends ReddImtDAOSQL implements ReplyDAO {

    private static final String SELECT_REPLY = "SELECT reddimt_reply.ID, reddimt_reply.ID_POST, reddimt_reply.ID_REPLY, reddimt_reply.CONTENT, reddimt_reply.CREATED_AT, reddimt_reply.USER_NAME  FROM REDDIMT_REPLY";

    private static final String POST_ID_CONDITION = " WHERE ID_POST = :postId";

    private static final String REPLY_OF_REPLY_ID_CONDITION = " WHERE ID_REPLY = :replyId";

    private static final String INNER_JOIN_ON_POST_REPLY = " INNER JOIN reddimt_post ON reddimt_post.id = reddimt_reply.id_post";

    private static final String REPLY_ID_CONDITION = " WHERE ID = :replyId";

    private static final String REPLY_ID_NULL = " AND ID_REPLY is null";

    private static final String INSERT_REPLY = " INSERT INTO REDDIMT_REPLY VALUES(:id, :postId, :replyId, :content, :userName, :createdAt)";

    private static final String UPDATE_REPLY = " UPDATE REDDIMT_REPLY SET ID=: id, ID_POST=:postId, ID_REPLY=:replyId, CONTENT=:content, USER_NAME=:userName, CREATED_AT=:createdAt";

    private static final String DELETE_REPLY = " DELETE FROM REDDIMT_REPLY";

    private static final RowMapper<Reply> REPLY_MAPPER = new ReplyMapper();

    public ReplyDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }


    @Override
    public Optional<Reply> getReplyById(String replyId) {
        var source = new MapSqlParameterSource("replyId", replyId);
        return queryOptional(SELECT_REPLY+REPLY_ID_CONDITION, source, REPLY_MAPPER);
    }

    @Override
    public void create(Reply reply) {
        var source = new MapSqlParameterSource();
        source.addValue("id", reply.getId());
        source.addValue("postId", reply.getPostId());
        source.addValue("replyId", reply.getReplyId());
        source.addValue("content", reply.getContent());
        source.addValue("userName", reply.getUserName());
        source.addValue("createdAt", reply.getCreatedAt());
        getJdbcTemplate().update(INSERT_REPLY, source);
    }

    @Override
    public Reply update(Reply reply) {
        var source = new MapSqlParameterSource();
        source.addValue("id", reply.getId());
        source.addValue("postId", reply.getPostId());
        source.addValue("replyId", reply.getReplyId());
        source.addValue("content", reply.getContent());
        source.addValue("userName", reply.getUserName());
        source.addValue("createdAt", reply.getCreatedAt());
        getJdbcTemplate().update(UPDATE_REPLY+REPLY_ID_CONDITION, source);

        return getReplyById(reply.getId()).orElse(null);
    }

    @Override
    public void delete(String id) {
        SqlParameterSource source = new MapSqlParameterSource("replyId",id);
        getJdbcTemplate().update(DELETE_REPLY+REPLY_ID_CONDITION, source);
    }


    @Override
    public List<Reply> getRepliesByPostId(String postId) {
        SqlParameterSource source = new MapSqlParameterSource("postId", postId);
        return getJdbcTemplate().query(SELECT_REPLY+INNER_JOIN_ON_POST_REPLY+POST_ID_CONDITION+ REPLY_ID_NULL, source, REPLY_MAPPER);
    }

    @Override
    public List<Reply> getCommentsByReplyId(String replyId) {
        SqlParameterSource source = new MapSqlParameterSource("replyId", replyId);
        return getJdbcTemplate().query(SELECT_REPLY+ REPLY_OF_REPLY_ID_CONDITION, source, REPLY_MAPPER);
    }


}
