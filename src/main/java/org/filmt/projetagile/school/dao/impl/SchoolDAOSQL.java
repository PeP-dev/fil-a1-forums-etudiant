package org.filmt.projetagile.school.dao.impl;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.groups.model.Group;
import org.filmt.projetagile.school.dao.SchoolDAO;
import org.filmt.projetagile.school.model.School;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;


public class SchoolDAOSQL extends ReddImtDAOSQL implements SchoolDAO {

    private static final String SELECT_SCHOOL = "SELECT  FROM REDDIMT_SCHOOL";

    private static final String SCHOOL_ID_CONDITION = " WHERE ID = :schoolId";

    private static final String TYPE_CONDITION = " WHERE SCHOOL_TYPE =:typeId";

    private static final String INSERT_SCHOOL = "INSERT INTO REDDIMT_SCHOOL VALUES(:id, :libelle, :school_type, :description)";

    private static final String UPDATE_SCHOOL = "UPDATE REDDIMT_SCHOOL SET ID=: id, LIBELLE=:schoolId, SCHOOL_TYPE=:school_type, DESCRIPTION=:description";

    private static final String DELETE_SCHOOL = "DELETE FROM REDDIMT_SCHOOL";

    private static final RowMapper<Group> School_MAPPER = (rs, ri)-> new Group(
            rs.getString("ID"),
            rs.getString("LIBELLE"),
            rs.getString("SCHOOL_TYPE"),
            rs.getString("DESCRIPTION"));

    public SchoolDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public School getSchoolById(String schoolId) {
        return null;
    }

    @Override
    public List<Group> getSchoolByTypeId(String typeId) {
        SqlParameterSource source = new MapSqlParameterSource("school_type", typeId);
        return getJdbcTemplate().query(SELECT_SCHOOL+SCHOOL_ID_CONDITION, source, School_MAPPER);
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
        getJdbcTemplate().update(INSERT_SCHOOL, source, keyHolder);
        return new School(keyHolder.getKeyAs(String.class), school.getLibelle(), school.getSchool_type(), school.getDescription());
    }

    @Override
    public School update(School school) {
        var source = new MapSqlParameterSource();
        source.addValue("libelle", school.getLibelle());
        source.addValue("school_type", school.getSchool_type());
        source.addValue("description", school.getDescription());
        getJdbcTemplate().update(UPDATE_SCHOOL+SCHOOL_ID_CONDITION, source);

        return getSchoolById(school.getId());
    }

    @Override
    public void delete(String id) {
        SqlParameterSource source = new MapSqlParameterSource("schoolId",id);
        getJdbcTemplate().update(DELETE_SCHOOL+SCHOOL_ID_CONDITION, source);
    }
}
