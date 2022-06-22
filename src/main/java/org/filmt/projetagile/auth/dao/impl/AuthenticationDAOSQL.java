package org.filmt.projetagile.auth.dao.impl;

import org.filmt.projetagile.auth.dao.AuthenticationDAO;
import org.filmt.projetagile.auth.model.GroupRole;
import org.filmt.projetagile.common.jdbc.mapper.UserModelMapper;
import org.filmt.projetagile.user.model.UserModel;
import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.exception.UserAlreadyExistsException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationDAOSQL extends ReddImtDAOSQL implements AuthenticationDAO {

    private static final String INSERT_USER = "INSERT INTO REDDIMT_USER VALUES(:userName, :userName, :pwd, NULL, NULL, NULL, false)";

    private static final String SELECT_USER = "SELECT * FROM REDDIMT_USER";

    private static final String WHERE_USERNAME = " WHERE user_name = :userName";

    private static final RowMapper<UserModel> ROW_MAPPER = new UserModelMapper();
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
        return queryOptional(SELECT_USER+WHERE_USERNAME, source, ROW_MAPPER);
    }

    @Override
    public void registerUser(UserModel model) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("userName",model.getUsername());
        source.addValue("pwd",model.getPassword());
        try {
            getJdbcTemplate().update(INSERT_USER, source);
        } catch(DuplicateKeyException e) {
            log.error("Couldn't register a new user : ", e);
            throw new UserAlreadyExistsException(String.format("The username %s is already taken", model.getUsername()));
        }
    }
}
