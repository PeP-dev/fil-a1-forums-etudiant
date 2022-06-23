package org.filmt.projetagile.roles.dao.impl;

import org.filmt.projetagile.common.jdbc.mapper.GroupMapper;
import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.roles.dao.RoleDAOSQL;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOSQLGroup extends RoleDAOSQL<Group> {

    public RoleDAOSQLGroup(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    protected String getEntityTableName() {
        return "REDDIMT_GROUP";
    }

    @Override
    protected String getEntityColumnName() {
        return "ID_GROUP";
    }

    @Override
    protected String getRoleAssociationTableName() {
        return "REDDIMT_ROLE_GROUPE";
    }

    @Override
    protected RowMapper<Group> getRowMapper() {
        return new GroupMapper();
    }
}
