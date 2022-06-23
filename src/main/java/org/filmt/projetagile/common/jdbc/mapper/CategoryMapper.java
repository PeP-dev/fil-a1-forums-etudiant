package org.filmt.projetagile.common.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.util.Strings;
import org.filmt.projetagile.groups.model.Category;
import org.springframework.jdbc.core.RowMapper;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryMapper implements RowMapper<Category> {
    private String prefix = Strings.EMPTY;

    public CategoryMapper(String tablePrefix) {
        this.prefix = tablePrefix+".";
    }
    @Override
    public Category mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        String id = rs.getString("CAT_ID");
        if (id == null) {
            return null;
        }
        return new Category(id, rs.getString("CAT_LIBELLE"), rs.getString("CAT_DESCRIPTION"));
    }
}
