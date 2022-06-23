package org.filmt.projetagile.common.jdbc.extractors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.filmt.projetagile.groups.model.Category;
import org.filmt.projetagile.groups.model.Group;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupCategoryExtractor implements ResultSetExtractor<List<Group>> {
    RowMapper<Group> groupRowMapper;
    RowMapper<Category> categoryRowMapper;

    @Override
    public List<Group> extractData(final ResultSet rs) throws SQLException, DataAccessException {
        List<Group> groups = new ArrayList<>();
        Group currentGroup = null;
        while(rs.next()) {
            String id = rs.getString("id");
            if (currentGroup == null) { // initial object
                currentGroup = groupRowMapper.mapRow(rs,rs.getRow());
            } else if (!currentGroup.getId().equals(id)) { // break*
                groups.add(currentGroup);
                currentGroup = groupRowMapper.mapRow(rs,rs.getRow());
            }
            Category cat = categoryRowMapper.mapRow(rs,rs.getRow());
            if (cat != null && currentGroup != null) {
                currentGroup.getCategories().add(cat);
            }
        }
        if (currentGroup != null) { // last object
            groups.add(currentGroup);
        }
        return groups;
    }
}
