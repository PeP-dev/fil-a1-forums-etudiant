package org.filmt.projetagile.posts.dao.impl;

import java.util.Collections;
import java.util.List;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.posts.dao.PostDAO;
import org.filmt.projetagile.posts.model.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAOSQL extends ReddImtDAOSQL implements PostDAO {
    private static final String SELECT_POST = "SELECT ID, ID_GROUP, TITLE, POST_CONTENT, ID_CATEGORY FROM REDDIMT_POST";

    private static final String GROUP_ID_CONDITION = " WHERE ID_GROUP = :groupId";

    private static final String POST_ID_CONDITION = " WHERE ID = :postId";

    private static final String CATEGORY_CONDITION = " WHERE ID_CATEGORY =:categoryId";

    private static final String INSERT_POST = "INSERT INTO REDDIMT_POST VALUES(:groupId, :title, :content, :categoryId)";

    private static final String UPDATE_POST = "UPDATE REDDIMT_POST SET ID_GROUP=:groupId, TITLE=:title, POST_CONTENT=:content, ID_CATEGORY=:categoryId";

    private static final String DELETE_POST = "DELETE FROM REDDIMT_POST";

    private static final RowMapper<Post> POST_MAPPER = (rs, ri)-> new Post(
        rs.getString("ID"),
        rs.getString("ID_GROUP"),
        rs.getString("TITLE"),
        rs.getString("POST_CONTENT"),
        rs.getString("ID_CATEGORY"));
    public PostDAOSQL(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public Post getPostById(final String postId) {
        return null;
    }

    @Override
    public List<Post> getPostsByGroupId(final String groupId) {
        SqlParameterSource source = new MapSqlParameterSource("groupId", groupId);
        return getJdbcTemplate().query(SELECT_POST+GROUP_ID_CONDITION, source, POST_MAPPER);
    }

    @Override
    public List<Post> getPostByCategory(final String groupId, final String categoryId) {
        SqlParameterSource source = new MapSqlParameterSource("categoryId", categoryId);
        return getJdbcTemplate().query(SELECT_POST+CATEGORY_CONDITION, source, POST_MAPPER);
    }

    @Override
    public List<Post> getPostsBySchoolIdAndTitle(final String schoolId, final String title) {
        return Collections.emptyList();
    }

    @Override
    public List<Post> getPostsByGroupIdAndTitle(final String groupId, final String title) {
        return Collections.emptyList();
    }

    @Override
    public Post create(final Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var source = new MapSqlParameterSource();
        source.addValue("groupId", post.getGroupId());
        source.addValue("title", post.getTitle());
        source.addValue("content", post.getContent());
        source.addValue("categoryId", post.getCategoryId());
        getJdbcTemplate().update(INSERT_POST, source, keyHolder);
        return new Post(keyHolder.getKeyAs(String.class), post.getGroupId(), post.getTitle(), post.getContent(), post.getCategoryId());
    }

    @Override
    public Post update(final Post post) {
        var source = new MapSqlParameterSource();
        source.addValue("groupId", post.getGroupId());
        source.addValue("title", post.getTitle());
        source.addValue("content", post.getContent());
        source.addValue("categoryId", post.getCategoryId());
        source.addValue("postId", post.getId());
        getJdbcTemplate().update(UPDATE_POST+POST_ID_CONDITION, source);

        return getPostById(post.getId());
    }

    @Override
    public void delete(final String id) {
        SqlParameterSource source = new MapSqlParameterSource("postId",id);
        getJdbcTemplate().update(DELETE_POST+POST_ID_CONDITION, source);
    }
}
