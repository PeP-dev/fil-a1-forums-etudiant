package org.filmt.projetagile.likes.dao;

import java.util.List;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.common.jdbc.mapper.UserModelMapper;
import org.filmt.projetagile.user.model.UserModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class LikeDAOSQL<T> extends ReddImtDAOSQL implements LikeDAO<T> {

    private static final RowMapper<Integer> COUNT_ROW_MAPPER = (resultSet, rowIndex) -> resultSet.getInt("count_column");

    protected LikeDAOSQL(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    public abstract String getDaoTableName();

    public abstract String getContentTableName();

    public abstract String getContentPk();

    public abstract String getContentColumnName();

    public abstract String getUserColumnName();

    protected abstract RowMapper<T> getContentRowMapper();

    public List<T> getLikedContentByUser(String userId) {
        var source = new MapSqlParameterSource();
        source.addValue("id", userId);
        return getJdbcTemplate().query(
            String.format("SELECT * FROM %1$s JOIN %2$s on %2$s.%3$s = %1$s.%4$s WHERE %1$s.%5$s = :id",
                getDaoTableName(),
                getContentTableName(),
                getContentPk(),
                getContentColumnName(),
                getUserColumnName()
                ),
            source, getContentRowMapper());
    }

    public int getContentLikeAmount(String contentId) {
        var source = new MapSqlParameterSource();
        source.addValue("id", contentId);
        return getJdbcTemplate().queryForObject(String.format("SELECT COUNT(*) as count_column FROM %s WHERE %s = :id", getDaoTableName(), getContentColumnName()), source, COUNT_ROW_MAPPER);
    }

    public List<UserModel> getContentLike(String contentId) {
        var source = new MapSqlParameterSource();
        source.addValue("id", contentId);
        return getJdbcTemplate().query(String.format("SELECT * FROM %1$s JOIN REDDIMT_USER ON REDDIMT_USER.USER_NAME = %1$s.%2$s WHERE %1$s.%3$s = :id",
            getDaoTableName(),
            getUserColumnName(),
            getContentColumnName()), source, new UserModelMapper());
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

    public void addLike(String userId, String contentId) {
        var source = new MapSqlParameterSource();
        source.addValue("contentId", contentId);
        source.addValue("userId", userId);
        getJdbcTemplate().update(String.format("INSERT INTO %s (%s, %s) VALUES (:contentId, :userId)", getDaoTableName(), getContentColumnName(), getUserColumnName()), source);
    }
}
