package org.filmt.projetagile.common;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ReddImtDAOSQL {
    private final NamedParameterJdbcTemplate template;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return template;
    }
}
