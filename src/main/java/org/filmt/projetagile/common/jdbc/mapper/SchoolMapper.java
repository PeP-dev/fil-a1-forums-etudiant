package org.filmt.projetagile.common.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.filmt.projetagile.school.model.School;
import org.springframework.jdbc.core.RowMapper;

public class SchoolMapper implements RowMapper<School> {

    @Override
    public School mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new School(
            rs.getString("ID"),
            rs.getString("LIBELLE"),
            rs.getString("SCHOOL_TYPE"),
            rs.getString("DESCRIPTION"));
    }
}
