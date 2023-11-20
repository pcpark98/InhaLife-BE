package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.domain.BoardBookmark;
import com.capstonedesign.inhalife.board.dto.request.CreateBoardBookmarkRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadBoardBookmarkResponse;
import com.capstonedesign.inhalife.board.service.BoardBookmarkService;
import com.capstonedesign.inhalife.board.service.BoardService;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "게시판 즐겨찾기 API", description = "게시판 즐겨찾기 관련 API")
@RestController
@RequiredArgsConstructor
public class BoardBookmarkController {

    private final UserService userService;
    private final BoardService boardService;
    private final BoardBookmarkService boardBookmarkService;

    @Operation(summary = "게시판 즐겨찾기 등록 API", description = "게시판을 즐겨찾기에 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "게시판을 즐겨찾기에 등록할 유저의 id"),
            @Parameter(name = "boardId", description = "즐겨찾기에 등록할 게시판의 id" )
    })
    @PostMapping("/board-bookmark")
    public ResponseEntity<Void> createBoardBookmark(
            @RequestBody @Valid CreateBoardBookmarkRequest request) {
        User user = userService.getById(request.getUserId());
        Board board = boardService.getById(request.getBoardId());

        BoardBookmark boardBookmark = new BoardBookmark(user, board);
        boardBookmarkService.createBoardBookmark(boardBookmark);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저가 즐겨찾기로 저장한 모든 게시판 조회 API", description = "유저가 즐겨찾기로 저장한 모든 게시판을 조회합니다.")
    @Parameters({
            @Parameter(name = "userIndex", description = "즐겨찾기한 게시판을 조회할 유저의 id"),
            @Parameter(name = "page", description = "조회할 게시판이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 게시판의 개수" )
    })
    @GetMapping("/user/{userIndex}/board-bookmark")
    public ResponseEntity<List<ReadBoardBookmarkResponse>> readUsersBoardBookmark(
            @PathVariable Long userIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        User user = userService.getById(userIndex);

        List<ReadBoardBookmarkResponse> boardList = boardBookmarkService.getByUserId(userIndex, page, size);

        return ResponseEntity.ok(boardList);
    }

    @Operation(summary = "게시판 즐겨찾기 취소 API", description = "유저가 즐겨찾기로 저장했던 게시판의 즐겨찾기를 취소합니다.")
    @Parameter(name = "boardBookmarkIndex", description = "취소할 즐겨찾기의 id")
    @DeleteMapping("/board-bookmark/{boardBookmarkIndex}")
    public ResponseEntity<Void>  deleteBoardBookmark(
            @PathVariable Long boardBookmarkIndex) {
        BoardBookmark boardBookmark = boardBookmarkService.getById(boardBookmarkIndex);

        boardBookmarkService.deleteBoardBookmark(boardBookmark);

        return ResponseEntity.ok().build();
    }
}
