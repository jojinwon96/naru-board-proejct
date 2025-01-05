package com.board.server.board;

import com.board.server.board.dto.object.BoardListItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 게시글 전체 조회
     */
    public List<BoardListItem> findAll() {
        String sql = "SELECT id, title, content, create_date FROM tbl_board";

        RowMapper<BoardListItem> rowMapper = (rs, rowNum) -> new BoardListItem(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("create_date").toLocalDateTime()
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
