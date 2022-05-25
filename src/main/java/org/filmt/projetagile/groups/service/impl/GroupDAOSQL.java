package org.filmt.projetagile.groups.service.impl;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.groups.dao.GroupDAO;
import org.filmt.projetagile.groups.model.Group;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class GroupDAOSQL extends ReddImtDAOSQL implements GroupDAO {

    private static final String SELECT_GROUP = "SELECT ID, ID_ECOLE, LIBELLE, DESCRIPTION FROM REDDIMT_GROUP";

    private static final String GROUP_ID_CONDITION = " WHERE ID = :groupId";

    private static final String SCHOOL_ID_CONDITION = " WHERE ID_SCHOOL = :schoolId";

    private static final String INSERT_GROUP = "INSERT INTO REDDIMT_GROUP VALUES(:id, :schoolId, :label, :description)";

    private static final String UPDATE_GROUP = "UPDATE REDDIMT_GROUP SET ID=: id, ID_ECOLE=:schoolId, LIBELLE=:libelle, DESCRIPTION=:description";

    private static final String DELETE_GROUP = "DELETE FROM REDDIMT_GROUP";

    private static final RowMapper<Group> Group_MAPPER = (rs, ri)-> new Group(
            rs.getString("ID"),
            rs.getString("ID_SCHOOL"),
            rs.getString("LABEL"),
            rs.getString("DESCRIPTION"));

    public GroupDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public Group getGroupById(String postId) {
        return null;
    }

    @Override
    public List<Group> getGroupBySchoolId(String schoolID) {
        SqlParameterSource source = new MapSqlParameterSource("schoolId", schoolID);
        return getJdbcTemplate().query(SELECT_GROUP+SCHOOL_ID_CONDITION, source, Group_MAPPER);
    }

    @Override
    public Group create(final Group group) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var source = new MapSqlParameterSource();
        source.addValue("label", group.getLabel());
        source.addValue("description", group.getDescription());
        source.addValue("schoolId", group.getSchoolId());
        getJdbcTemplate().update(INSERT_GROUP, source, keyHolder);
        return new Group(keyHolder.getKeyAs(String.class), group.getSchoolId(), group.getLabel(), group.getDescription());

    }

    @Override
    public Group update(Group group) {
        var source = new MapSqlParameterSource();
        source.addValue("label", group.getLabel());
        source.addValue("description", group.getDescription());
        source.addValue("schoolId", group.getSchoolId());
        getJdbcTemplate().update(UPDATE_GROUP+GROUP_ID_CONDITION, source);

        return getGroupById(group.getId());
    }

    @Override
    public void delete(String id) {
        SqlParameterSource source = new MapSqlParameterSource("groupId",id);
        getJdbcTemplate().update(DELETE_GROUP+GROUP_ID_CONDITION, source);
    }
}
