package org.filmt.projetagile.common.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.filmt.projetagile.roles.model.Role;
import org.springframework.jdbc.core.RowMapper;

public class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        int val = rs.getInt("ID");
        return Arrays.stream(Role.values()).filter(role->role.getCode() == val).findFirst().orElseThrow(()->new RuntimeException("Invalid Role Code"));
    }
}
