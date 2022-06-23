package org.filmt.projetagile.user.dao.impl;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.common.jdbc.mapper.UserModelMapper;
import org.filmt.projetagile.user.dao.UserDao;
import org.filmt.projetagile.user.model.UserModel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOSQL extends ReddImtDAOSQL implements UserDao {

    private static final String SELECT_USER = "SELECT * FROM REDDIMT_USER";

    private static final String WHERE_USERNAME = " WHERE USER_NAME = :username";

    private static final String DELETE_USER = "DELETE FROM REDDIMT_USER";

    private static final RowMapper<UserModel> ROW_MAPPER = new UserModelMapper();

    private static final String UPDATE_USER = "UPDATE REDDIMT_USER SET USER_NAME= :username, PSEUDO=:pseudo, EMAIL=:email, AVATAR_URL=:avatarUrl, NOTE_PERSO=:note";


    public UserDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public Optional<UserModel> getUserByUsername(String username) {
        SqlParameterSource source = new MapSqlParameterSource("username",username);
        return queryOptional(SELECT_USER+WHERE_USERNAME, source, ROW_MAPPER);
    }

    @Override
    public List<UserModel> getUsers() {
        return getJdbcTemplate().query(SELECT_USER, ROW_MAPPER);
    }

    @Override
    public UserModel update(UserModel user) {
        var source = new MapSqlParameterSource();
        source.addValue("email", user.getEmail());
        source.addValue("password", user.getPassword());
        source.addValue("avatarUrl", user.getAvatarUrl());
        source.addValue("note", user.getNote());
        source.addValue("pseudo", user.getPseudo());
        getJdbcTemplate().update(UPDATE_USER +WHERE_USERNAME, source);

        return user;
    }

    @Override
    public void delete(String username) {
        SqlParameterSource source = new MapSqlParameterSource("username",username);
        getJdbcTemplate().update(DELETE_USER+WHERE_USERNAME, source);
    }
}
