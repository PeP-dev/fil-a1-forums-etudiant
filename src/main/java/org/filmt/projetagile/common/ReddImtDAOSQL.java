package org.filmt.projetagile.common;

import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ReddImtDAOSQL {
    private final NamedParameterJdbcTemplate template;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return template;
    }

    public <T> Optional<T> queryOptional(String query, SqlParameterSource source, RowMapper<T> mapper) {
        try {
            return Optional.ofNullable(getJdbcTemplate().queryForObject(query, source, mapper));
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }
}
