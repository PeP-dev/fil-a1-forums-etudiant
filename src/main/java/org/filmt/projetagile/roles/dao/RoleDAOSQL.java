package org.filmt.projetagile.roles.dao;

import java.util.List;
import java.util.Optional;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.common.jdbc.mapper.RoleMapper;
import org.filmt.projetagile.roles.model.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class RoleDAOSQL<T> extends ReddImtDAOSQL implements RoleDAO<T>{

    private static final String DEFAULT_ENTITY_PK = "ID";

    private static final String DEFAULT_USER_NAME_COLUMN = "USER_NAME";

    private static final String DEFAULT_ROLE_COLUMN_NAME = "ID_ROLE";

    protected abstract String getEntityTableName();

    protected abstract String getEntityColumnName();

    protected String getRoleColumnName() {
        return DEFAULT_ROLE_COLUMN_NAME;
    }

    protected String getUserColumnName() {
        return DEFAULT_USER_NAME_COLUMN;
    }

    protected abstract String getRoleAssociationTableName();

    protected abstract RowMapper<T> getRowMapper();

    public RoleDAOSQL(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public List<T> getByAnyRole(final String userId) {
        var source = new MapSqlParameterSource("userId", userId);
        return getJdbcTemplate().query(
            String.format("SELECT * FROM %1$s JOIN %3$s ON %3$s.%4$s = %1$s.%5$s WHERE %2$s = :userId ",
                getRoleAssociationTableName(),
                getUserColumnName(),
                getEntityTableName(),
                getEntityPk(),
                getEntityColumnName()),
            source,
            getRowMapper());
    }

    @Override
    public List<T> getByRole(final String userId, final Role role) {
        var source = new MapSqlParameterSource();
        source.addValue("userId", userId);
        source.addValue("roleCode", Integer.toString(role.getCode()));

        return getJdbcTemplate().query(
            String.format("SELECT * FROM %1$s JOIN %4$s ON %4$s.%5$s = %1$s.%6$s WHERE %2$s = :userId and %3$s = :roleCode",
                getRoleAssociationTableName(),
                getUserColumnName(),
                getRoleColumnName(),
                getEntityTableName(),
                getEntityPk(),
                getEntityColumnName()),
            source,
            getRowMapper()
        );
    }

    @Override
    public Optional<Role> getRoleForEntity(final String userId, final String entityId) {
        var source = new MapSqlParameterSource();
        source.addValue("userId", userId);
        source.addValue("entityId", entityId);

        return queryOptional(
            String.format("SELECT ID_ROLE as ID FROM %s WHERE %s = :userId and %s = :entityId",
                getRoleAssociationTableName(),
                getUserColumnName(),
                getEntityColumnName()),
            source,
            new RoleMapper()
        );
    }

    @Override
    public void addRole(final String userId, final String entityId, final Role role) {
        var source = new MapSqlParameterSource();
        source.addValue("userId", userId);
        source.addValue("roleCode", role.getCode());
        source.addValue("entityId", entityId);
        getJdbcTemplate().update(
            String.format("INSERT INTO %s(%s, %s, %s) VALUES(:userId, :roleCode, :entityId)",
                getRoleAssociationTableName(),
                getUserColumnName(),
                getRoleColumnName(),
                getEntityColumnName()
                ),source);
    }

    @Override
    public void removeRole(final String userId, final String entityId) {
        var source = new MapSqlParameterSource();
        source.addValue("userId", userId);
        source.addValue("entityId", entityId);
        getJdbcTemplate().update(
            String.format("DELETE FROM %s WHERE %s = :userId and %s = :entityId",
                getRoleAssociationTableName(),
                getUserColumnName(),
                getEntityColumnName()
            ),source);
    }

    @Override
    public void changeRole(final String userId, final String entityId, final Role role) {
        var source = new MapSqlParameterSource();
        source.addValue("userId", userId);
        source.addValue("entityId", entityId);
        source.addValue("roleCode", role.getCode());
        getJdbcTemplate().update(
            String.format("UPDATE %s SET %s = :roleCode WHERE %s = :userId and %s = :entityId",
                getRoleAssociationTableName(),
                getRoleColumnName(),
                getUserColumnName(),
                getEntityColumnName()
            ),source);
    }


    protected String getEntityPk() {
        return DEFAULT_ENTITY_PK;
    }
}
