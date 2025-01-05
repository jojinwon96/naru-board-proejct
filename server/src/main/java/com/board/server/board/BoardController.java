package com.board.server.board;

import com.board.server.board.dto.response.GetBoardListResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping("/board/list")
    public ResponseEntity<GetBoardListResponseDto> getBoardList() {
        ResponseEntity<GetBoardListResponseDto> result = boardService.getBoardList();
        return result;
    }
}
