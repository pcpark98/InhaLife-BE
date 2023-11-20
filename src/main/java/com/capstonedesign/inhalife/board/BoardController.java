package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.dto.request.CreateBoardRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardResponse;
import com.capstonedesign.inhalife.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<Void> createBoard(
            @RequestBody @Valid CreateBoardRequest request) {
        Board board = new Board(request.getName());

        boardService.createBoard(board);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/board")
    public ResponseEntity<List<ReadBoardResponse>> readBoard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadBoardResponse> boardList = boardService.getAll(page, size);
        return ResponseEntity.ok(boardList);
    }

    @DeleteMapping("/board/{boardIndex}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long boardIndex) {
        boardService.deleteBoard(boardIndex);

        return ResponseEntity.ok().build();
    }
}
