package com.board.server.board.dto.response;

import com.board.server.board.common.ResponseCode;
import com.board.server.board.common.ResponseMessage;
import com.board.server.board.dto.object.BoardListItem;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetBoardListResponseDto extends ResponseDTO {

    private List<BoardListItem> boardListItemList;

    private GetBoardListResponseDto(List<BoardListItem> boardListItem) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardListItemList= boardListItem;
    }

    public static ResponseEntity<GetBoardListResponseDto> success(List<BoardListItem> boardListItems) {
        GetBoardListResponseDto result = new GetBoardListResponseDto(boardListItems);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
