package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleBookmark;
import com.capstonedesign.inhalife.board.dto.request.CreateArticleBookmarkRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleBookmarkResponse;
import com.capstonedesign.inhalife.board.service.ArticleBookmarkService;
import com.capstonedesign.inhalife.board.service.ArticleService;
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

@Tag(name = "게시글 북마크 API", description = "게시글 북마크 관련 API")
@RestController
@RequiredArgsConstructor
public class ArticleBookmarkController {

    private final ArticleBookmarkService articleBookmarkService;
    private final UserService userService;
    private final ArticleService articleService;

    @Operation(summary = "게시글 스크랩 하기 API", description = "게시글을 스크랩해서 저장합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "스크랩 할 유저의 id"),
            @Parameter(name = "articleId", description = "스크랩할 게시글의 id" )
    })
    @PostMapping("/article-bookmark")
    public ResponseEntity<Void> createArticleBookmark(
            @RequestBody @Valid CreateArticleBookmarkRequest request) {
        User user = userService.getById(request.getUserId());
        Article article = articleService.getById(request.getArticleId());

        ArticleBookmark articleBookmark = new ArticleBookmark(user, article);
        articleBookmarkService.createArticleBookmark(articleBookmark);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저가 스크랩한 모든 게시글 조회 API", description = "유저가 스크랩한 모든 게시글을 조회합니다.")
    @Parameters({
            @Parameter(name = "userIndex", description = "스크랩한 게시글을 조회할 유저의 id"),
            @Parameter(name = "page", description = "스크랩한 게시글이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 게시글의 개수" )
    })
    @GetMapping("/user/{userIndex}/article-bookmark")
    public ResponseEntity<List<ReadArticleBookmarkResponse>> readUsersArticleBookmark(
            @PathVariable Long userIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        User user = userService.getById(userIndex);

        List<ReadArticleBookmarkResponse> articleBookmarkList = articleBookmarkService.getAllArticleBookmarkByUserId(userIndex, page, size);

        return ResponseEntity.ok(articleBookmarkList);
    }

    @DeleteMapping("/article-bookmark/{articleBookmarkInex}")
    public ResponseEntity<Void> deleteArticleBookmark(
            @PathVariable Long articleBookmarkIndex) {
        ArticleBookmark articleBookmark = articleBookmarkService.getById(articleBookmarkIndex);

        articleBookmarkService.deleteArticleBookmark(articleBookmark);

        return ResponseEntity.ok().build();
    }
}
