package org.filmt.projetagile.common.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.filmt.projetagile.replies.model.Reply;
import org.springframework.jdbc.core.RowMapper;

public class ReplyMapper implements RowMapper<Reply> {

    @Override
    public Reply mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Reply(
            rs.getString("ID"),
            rs.getString("ID_POST"),
            rs.getString("ID_REPLY"),
            rs.getString("CONTENT"),
            rs.getString("USER_NAME"),
            rs.getTimestamp("CREATED_AT"));
    }
}
