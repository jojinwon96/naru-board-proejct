package com.board.server.board;

import com.board.server.board.dto.object.BoardListItem;
import com.board.server.board.dto.request.PatchBoardRequestDTO;
import com.board.server.board.dto.request.PostBoardRequestDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
        String sql = "SELECT id, title, content, create_date FROM tbl_board ORDER BY create_date DESC";

        RowMapper<BoardListItem> rowMapper = (rs, rowNum) -> new BoardListItem(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("create_date").toLocalDateTime()
        );

        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * 게시글 하나 삽입
     */
    public int save(PostBoardRequestDTO board) {
        String sql = "INSERT INTO tbl_board (title, content, create_date) VALUES (?, ?, ?)";

        LocalDateTime currentTime = LocalDateTime.now();

        return jdbcTemplate.update(sql, board.getTitle(), board.getContent(), currentTime);
    }

    /**
     * 게시글 하나 수정
     */
    public int update(Long id, PatchBoardRequestDTO board) {
        String sql = "UPDATE tbl_board SET title = ?, content = ? WHERE id = ?";

        LocalDateTime currentTime = LocalDateTime.now();

        return jdbcTemplate.update(sql, board.getTitle(), board.getContent(), id);
    }

    public BoardListItem findById(Long id) {
        String sql = "SELECT id, title, content, create_date FROM tbl_board WHERE id = ?";

        RowMapper<BoardListItem> rowMapper = (rs, rowNum) -> new BoardListItem(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("create_date").toLocalDateTime()
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


}
