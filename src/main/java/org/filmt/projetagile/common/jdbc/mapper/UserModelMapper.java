package org.filmt.projetagile.common.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.filmt.projetagile.user.model.UserModel;
import org.springframework.jdbc.core.RowMapper;

public class UserModelMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new UserModel(
            rs.getString("user_name"),
            rs.getString("password"),
            rs.getString("email"),
            rs.getString("avatar_url"),
            rs.getString("note_perso"),
            rs.getBoolean("global_admin")
        );
    }
}
