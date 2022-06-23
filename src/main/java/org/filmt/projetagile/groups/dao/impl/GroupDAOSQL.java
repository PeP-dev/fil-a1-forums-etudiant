package org.filmt.projetagile.groups.dao.impl;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.common.jdbc.mapper.GroupMapper;
import org.filmt.projetagile.groups.dao.GroupDAO;
import org.filmt.projetagile.groups.model.Group;
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
public class GroupDAOSQL extends ReddImtDAOSQL implements GroupDAO {

    private static final String SELECT_GROUP = "SELECT ID, ID_ECOLE, LIBELLE, DESCRIPTION FROM REDDIMT_GROUP";

    private static final String GROUP_ID_CONDITION = " WHERE ID = :groupId";

    private static final String SCHOOL_ID_CONDITION = " WHERE ID_ECOLE = :schoolId";

    private static final String INSERT_GROUP = "INSERT INTO REDDIMT_GROUP VALUES(:id, :schoolId, :label, :description)";

    private static final String UPDATE_GROUP = "UPDATE REDDIMT_GROUP SET ID= :id, ID_ECOLE=:schoolId, LIBELLE=:libelle, DESCRIPTION=:description";

    private static final String DELETE_GROUP = "DELETE FROM REDDIMT_GROUP";

    private static final RowMapper<Group> GROUP_MAPPER = new GroupMapper();

    public GroupDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public Optional<Group> getGroupById(String groupId) {
        SqlParameterSource source = new MapSqlParameterSource("groupId", groupId);
        return queryOptional(SELECT_GROUP+GROUP_ID_CONDITION, source, GROUP_MAPPER);
    }

    @Override
    public List<Group> getGroupBySchoolId(String schoolID) {
        SqlParameterSource source = new MapSqlParameterSource("schoolId", schoolID);
        return getJdbcTemplate().query(SELECT_GROUP+SCHOOL_ID_CONDITION, source, GROUP_MAPPER);
    }

    @Override
    public Group create(final Group group) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var source = new MapSqlParameterSource();
        source.addValue("label", group.getLabel());
        source.addValue("description", group.getDescription());
        source.addValue("schoolId", group.getSchoolId());
        source.addValue("id", group.getId());
        getJdbcTemplate().update(INSERT_GROUP, source, keyHolder);
        return group;
    }

    @Override
    public Group update(Group group) {
        var source = new MapSqlParameterSource();
        source.addValue("libelle", group.getLabel());
        source.addValue("description", group.getDescription());
        source.addValue("schoolId", group.getSchoolId());
        source.addValue("id", group.getId());
        source.addValue("groupId", group.getId());
        getJdbcTemplate().update(UPDATE_GROUP+GROUP_ID_CONDITION, source);

        return group;
    }

    @Override
    public void delete(String id) {
        SqlParameterSource source = new MapSqlParameterSource("groupId",id);
        getJdbcTemplate().update(DELETE_GROUP+GROUP_ID_CONDITION, source);
    }
}
