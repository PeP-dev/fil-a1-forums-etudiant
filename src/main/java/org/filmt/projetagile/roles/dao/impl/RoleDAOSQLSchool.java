package org.filmt.projetagile.roles.dao.impl;

import org.filmt.projetagile.common.jdbc.mapper.SchoolMapper;
import org.filmt.projetagile.roles.dao.RoleDAOSQL;
import org.filmt.projetagile.school.model.School;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOSQLSchool extends RoleDAOSQL<School> {

    public RoleDAOSQLSchool(final NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    protected String getEntityTableName() {
        return "REDDIMT_SCHOOL";
    }

    @Override
    protected String getEntityColumnName() {
        return "ID_SCHOOL";
    }

    @Override
    protected String getRoleAssociationTableName() {
        return "REDDIMT_ROLE_SCHOOL";
    }

    @Override
    protected RowMapper<School> getRowMapper() {
        return new SchoolMapper();
    }
}
