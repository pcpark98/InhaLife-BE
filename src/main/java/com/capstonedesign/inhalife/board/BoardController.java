package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.dto.request.CreateBoardRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardResponse;
import com.capstonedesign.inhalife.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "게시판 API", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시판 등록 API", description = "게시판을 등록합니다.")
    @Parameter(name = "name", description = "새로 등록할 게시판의 이름")
    @PostMapping("/board")
    public ResponseEntity<Void> createBoard(
            @RequestBody @Valid CreateBoardRequest request) {
        Board board = new Board(request.getName());

        boardService.createBoard(board);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "모든 게시판 조회 API", description = "모든 게시판을 조회합니다.")
    @Parameters({
            @Parameter(name = "page", description = "조회할 게시판이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 게시판의 개수" )
    })
    @GetMapping("/board")
    public ResponseEntity<List<ReadBoardResponse>> readBoard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadBoardResponse> boardList = boardService.getAll(page, size);
        return ResponseEntity.ok(boardList);
    }

    @Operation(summary = "게시판 삭제 API", description = "게시판을 삭제합니다.")
    @Parameter(name = "boardIndex", description = "삭제할 게시판의 id")
    @DeleteMapping("/board/{boardIndex}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long boardIndex) {
        boardService.deleteBoard(boardIndex);

        return ResponseEntity.ok().build();
    }
}
