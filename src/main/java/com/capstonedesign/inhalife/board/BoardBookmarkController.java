package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.domain.BoardBookmark;
import com.capstonedesign.inhalife.board.dto.request.CreateBoardBookmarkRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardResponse;
import com.capstonedesign.inhalife.board.service.BoardBookmarkService;
import com.capstonedesign.inhalife.board.service.BoardService;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardBookmarkController {

    private final UserService userService;
    private final BoardService boardService;
    private final BoardBookmarkService boardBookmarkService;

    @PostMapping("/board-bookmark")
    public ResponseEntity<Void> createBoardBookmark(
            @RequestBody @Valid CreateBoardBookmarkRequest request) {
        User user = userService.getById(request.getUserId());
        Board board = boardService.getById(request.getBoardId());

        BoardBookmark boardBookmark = new BoardBookmark(user, board);
        boardBookmarkService.createBoardBookmark(boardBookmark);

        return ResponseEntity.ok().build();
    }

    // TODO: 게시판 북마크 조회 DTO 변경
    @GetMapping("/user/{userIndex}/board-bookmark")
    public ResponseEntity<List<ReadBoardResponse>> readUsersBoardBookmark(
            @PathVariable Long userIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        User user = userService.getById(userIndex);

        List<ReadBoardResponse> boardList = boardBookmarkService.getByUserId(userIndex, page, size);

        return ResponseEntity.ok(boardList);
    }

    @DeleteMapping("/board-bookmark/{boardBookmarkIndex}")
    public ResponseEntity<Void>  deleteBoardBookmark(
            @PathVariable Long boardBookmarkIndex) {
        BoardBookmark boardBookmark = boardBookmarkService.getById(boardBookmarkIndex);

        boardBookmarkService.deleteBoardBookmark(boardBookmark);

        return ResponseEntity.ok().build();
    }
}
