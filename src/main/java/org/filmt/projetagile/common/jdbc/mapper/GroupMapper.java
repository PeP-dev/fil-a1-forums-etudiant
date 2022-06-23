package org.filmt.projetagile.common.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import org.filmt.projetagile.groups.model.Group;
import org.springframework.jdbc.core.RowMapper;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Group(
            rs.getString("ID"),
            rs.getString("ID_ECOLE"),
            rs.getString("LIBELLE"),
            rs.getString("DESCRIPTION"), new ArrayList<>());
    }
}
