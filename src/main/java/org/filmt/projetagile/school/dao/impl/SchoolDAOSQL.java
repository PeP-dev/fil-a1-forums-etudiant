package org.filmt.projetagile.school.dao.impl;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.common.jdbc.mapper.SchoolMapper;
import org.filmt.projetagile.exception.SchoolNotFoundException;
import org.filmt.projetagile.school.dao.SchoolDAO;
import org.filmt.projetagile.school.model.School;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SchoolDAOSQL extends ReddImtDAOSQL implements SchoolDAO {

    private static final String SELECT_SCHOOL = "SELECT ID, LIBELLE, SCHOOL_TYPE, DESCRIPTION FROM REDDIMT_SCHOOL";

    private static final String SCHOOL_ID_CONDITION = " WHERE ID = :schoolId";

    private static final String TYPE_CONDITION = " WHERE SCHOOL_TYPE =:typeId";

    private static final String INSERT_SCHOOL = "INSERT INTO REDDIMT_SCHOOL VALUES(:id, :libelle, :school_type, :description)";

    private static final String UPDATE_SCHOOL = "UPDATE REDDIMT_SCHOOL SET ID= :id, LIBELLE=:libelle, SCHOOL_TYPE=:school_type, DESCRIPTION=:description";

    private static final String DELETE_SCHOOL = "DELETE FROM REDDIMT_SCHOOL";

    private static final RowMapper<School> SCHOOL_MAPPER = new SchoolMapper();

    public SchoolDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public Optional<School> getSchoolById(String schoolId) {
        SqlParameterSource source = new MapSqlParameterSource("schoolId", schoolId);
        return queryOptional(SELECT_SCHOOL+SCHOOL_ID_CONDITION, source, SCHOOL_MAPPER);
    }

    @Override
    public List<School> getSchoolByTypeId(String typeId) {
        SqlParameterSource source = new MapSqlParameterSource("typeId", typeId);

        return getJdbcTemplate().query(SELECT_SCHOOL+TYPE_CONDITION, source, SCHOOL_MAPPER);
    }

    @Override
    public List<School> getAll() {
        return getJdbcTemplate().query(SELECT_SCHOOL, SCHOOL_MAPPER);
    }

    @Override
    public School getSchoolByLibelle(String libelle) {
        return null;
    }

    @Override
    public School create(School school) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var source = new MapSqlParameterSource();
        source.addValue("libelle", school.getLibelle());
        source.addValue("school_type", school.getSchool_type());
        source.addValue("description", school.getDescription());
        source.addValue("id", school.getId());

        getJdbcTemplate().update(INSERT_SCHOOL, source, keyHolder);
        return school;
    }

    @Override
    public School update(School school) {
        var source = new MapSqlParameterSource();
        source.addValue("libelle", school.getLibelle());
        source.addValue("school_type", school.getSchool_type());
        source.addValue("description", school.getDescription());
        source.addValue("schoolId", school.getId());
        source.addValue("id", school.getId());
        getJdbcTemplate().update(UPDATE_SCHOOL+SCHOOL_ID_CONDITION, source);

        return getSchoolById(school.getId()).orElse(null);
    }

    @Override
    public void delete(String id) {
        SqlParameterSource source = new MapSqlParameterSource("schoolId",id);
        getJdbcTemplate().update(DELETE_SCHOOL+SCHOOL_ID_CONDITION, source);
    }
}
