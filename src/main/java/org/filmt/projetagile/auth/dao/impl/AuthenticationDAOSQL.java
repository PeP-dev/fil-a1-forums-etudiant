package org.filmt.projetagile.auth.dao.impl;

import org.filmt.projetagile.auth.dao.AuthenticationDAO;
import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.auth.model.UserModel;
import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationDAOSQL extends ReddImtDAOSQL implements AuthenticationDAO {

    private static final String INSERT_USER = "INSERT INTO REDDIMT_USER VALUES(:userName, :userName, :pwd, NULL, NULL, NULL, false)";

    private static final String SELECT_USER = "SELECT * FROM REDDIMT_USER";

    private static final String WHERE_USERNAME = " WHERE user_name = :userName";

    private static final RowMapper<UserModel> ROW_MAPPER = (rs, rowNum) -> new UserModel(
            rs.getString("user_name"),
            rs.getString("password"),
            rs.getString("email"),
            rs.getString("avatar_url"),
            rs.getString("note_perso"),
            rs.getBoolean("global_admin")
    );
    public AuthenticationDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public GroupRole getRoleByUserNameAndGroupId(String userName, String groupId) {
        return null;
    }

    @Override
    public Optional<UserModel> loadByUsername(String userName) {
        SqlParameterSource source = new MapSqlParameterSource("userName", userName);
        return Optional.ofNullable(getJdbcTemplate().queryForObject(SELECT_USER+WHERE_USERNAME, source, ROW_MAPPER));
    }

    @Override
    public void registerUser(UserModel model) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("userName",model.getUsername());
        source.addValue("pwd",model.getPassword());
        getJdbcTemplate().update(INSERT_USER, source);
    }
}
