package org.filmt.projetagile.likes.dao;

import java.util.List;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class LikeDAOSQL<T> extends ReddImtDAOSQL implements LikeDAO<T> {

    private static final RowMapper<Integer> COUNT_ROW_MAPPER = (resultSet, rowIndex) -> resultSet.getInt("count_column");

    protected LikeDAOSQL(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    public abstract String getDaoTableName();

    public abstract String getContentColumnName();

    public abstract String getUserColumnName();

    protected abstract RowMapper<T> getRowMapper();

    public List<T> getLikedContentByUser(String userId) {
        var source = new MapSqlParameterSource();
        source.addValue("id", userId);
        source.addValue("userColumnName", getUserColumnName());
        source.addValue("likeTable", getDaoTableName());
        return getJdbcTemplate().query("SELECT * FROM :likeTable WHERE :userColumnName = :id", source, getRowMapper());
    }

    public int getLikeAmount(String contentId) {
        var source = new MapSqlParameterSource();
        source.addValue("id", contentId);
        source.addValue("contentColumnName", getContentColumnName());
        source.addValue("likeTable", getDaoTableName());
        return getJdbcTemplate().queryForObject("SELECT COUNT(*) as count_column FROM :likeTable WHERE :contentColumnName = :id", source, COUNT_ROW_MAPPER);
    }

    public void removeLike(String userId, String contentId) {
        var source = new MapSqlParameterSource();
        source.addValue("contentId", contentId);
        source.addValue("userId", userId);
        source.addValue("contentColumnName", getContentColumnName());
        source.addValue("userColumnName", getUserColumnName());
        source.addValue("likeTable", getDaoTableName());
        getJdbcTemplate().update("DELETE FROM :tableName WHERE :contentColumnName = :contentId AND :userColumnName = :userId", source);
    }
}
