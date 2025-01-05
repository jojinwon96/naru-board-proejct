package com.board.server.board;

import com.board.server.board.dto.object.BoardListItem;
import com.board.server.board.dto.response.GetBoardListResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public ResponseEntity<GetBoardListResponseDto> getBoardList() {
        List<BoardListItem> list = boardRepository.findAll();
        return GetBoardListResponseDto.success(list);
    }
}
