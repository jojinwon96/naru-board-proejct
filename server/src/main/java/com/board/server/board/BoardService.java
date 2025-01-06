package com.board.server.board;

import com.board.server.board.dto.object.BoardListItem;
import com.board.server.board.dto.request.PostBoardPaginationRequestDTO;
import com.board.server.board.dto.request.PatchBoardRequestDTO;
import com.board.server.board.dto.request.PostBoardRequestDTO;
import com.board.server.board.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * 게시글 전체 조회
     */
    public ResponseEntity<GetBoardListResponseDTO> getBoardList() {
        List<BoardListItem> list = boardRepository.findAll();
        return GetBoardListResponseDTO.success(list);
    }

    /**
     * 게시글 하나 삽입
     */
    public ResponseEntity<PostBoardResponseDto> postBoard(PostBoardRequestDTO board){
        int result = boardRepository.save(board);
        return PostBoardResponseDto.success();
    }

    /**
     * 게시글 하나 수정
     */
    public ResponseEntity<PatchBoardResponseDTO> updateBoard(Long id, PatchBoardRequestDTO board){
        int result = boardRepository.update(id, board);
        return PatchBoardResponseDTO.success();
    }

    /**
     * 게시글 하나 조회
     */
    public ResponseEntity<GetBoardResponseDTO> getBoard(Long id){
        BoardListItem board = boardRepository.findById(id);
        return GetBoardResponseDTO.success(board);
    }

    /**
     * 게시글 페이징 조회
     */
    public ResponseEntity<PostBoardPaginationResponseDTO> getBoardPagination(PostBoardPaginationRequestDTO request){
        int total = boardRepository.boardCount();
        List<BoardListItem> list = boardRepository.findAllByPage(request.getPage(), request.getSize());
        return PostBoardPaginationResponseDTO.success(total, list);
    }
}
