package org.filmt.projetagile.groups.dao.impl;

import org.filmt.projetagile.common.ReddImtDAOSQL;
import org.filmt.projetagile.common.jdbc.extractors.GroupCategoryExtractor;
import org.filmt.projetagile.common.jdbc.mapper.CategoryMapper;
import org.filmt.projetagile.common.jdbc.mapper.GroupMapper;
import org.filmt.projetagile.groups.dao.GroupDAO;
import org.filmt.projetagile.groups.model.Category;
import org.filmt.projetagile.groups.model.Group;
import org.springframework.jdbc.core.ResultSetExtractor;
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

    private static final String SELECT_GROUP = "SELECT REDDIMT_GROUP.id, REDDIMT_GROUP.id_ecole, REDDIMT_GROUP.libelle, REDDIMT_GROUP.description, REDDIMT_CATEGORY.ID AS CAT_ID, REDDIMT_CATEGORY.LIBELLE AS CAT_LIBELLE, REDDIMT_CATEGORY.DESCRIPTION AS CAT_DESCRIPTION FROM REDDIMT_GROUP";

    private static final String JOIN_CATEGORY = " FULL JOIN REDDIMT_CATEGORY ON REDDIMT_CATEGORY.ID_GROUP = REDDIMT_GROUP.ID";
    private static final String GROUP_ID_CONDITION = " WHERE REDDIMT_GROUP.ID = :groupId";

    private static final String SCHOOL_ID_CONDITION = " WHERE REDDIMT_GROUP.ID_ECOLE = :schoolId";

    private static final String ORDER_BY_ID = " ORDER BY REDDIMT_GROUP.ID";

    private static final String INSERT_GROUP = "INSERT INTO REDDIMT_GROUP VALUES(:id, :schoolId, :label, :description)";

    private static final String UPDATE_GROUP = "UPDATE REDDIMT_GROUP SET ID= :id, ID_ECOLE=:schoolId, LIBELLE=:libelle, DESCRIPTION=:description";

    private static final String DELETE_GROUP = "DELETE FROM REDDIMT_GROUP";

    private static final RowMapper<Group> GROUP_MAPPER = new GroupMapper();

    private static final ResultSetExtractor<List<Group>> GROUP_CATEGORY_EXTRACTOR = new GroupCategoryExtractor(GROUP_MAPPER, new CategoryMapper("REDDIMT_CATEGORY"));

    public GroupDAOSQL(NamedParameterJdbcTemplate template) {
        super(template);
    }

    @Override
    public Optional<Group> getGroupById(String groupId) {
        SqlParameterSource source = new MapSqlParameterSource("groupId", groupId);
        List<Group> result = getJdbcTemplate().query(SELECT_GROUP + JOIN_CATEGORY + GROUP_ID_CONDITION + ORDER_BY_ID, source, GROUP_CATEGORY_EXTRACTOR);
        if(result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    public void createCategory(String groupId, Category category) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("groupId", groupId);
        source.addValue("id", category.getId());
        source.addValue("desc", category.getDescription());
        source.addValue("libelle", category.getLibelle());
        getJdbcTemplate().update("INSERT INTO REDDIMT_CATEGORY(ID, ID_GROUP, LIBELLE, DESCRIPTION) VALUES(:id, :groupId, :desc, :libelle)", source);
    }

    @Override
    public void deleteCategory(final String groupId, final String categoryId) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("catId", categoryId);
        getJdbcTemplate().update("DELETE FROM REDDIMT_CATEGORY WHERE ID = :catId", source);

    }

    @Override
    public List<Group> getGroupBySchoolId(String schoolID) {
        SqlParameterSource source = new MapSqlParameterSource("schoolId", schoolID);
        return getJdbcTemplate().query(SELECT_GROUP + JOIN_CATEGORY + SCHOOL_ID_CONDITION + ORDER_BY_ID, source, GROUP_CATEGORY_EXTRACTOR);
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
