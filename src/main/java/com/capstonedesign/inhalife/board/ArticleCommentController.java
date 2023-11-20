package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleComment;
import com.capstonedesign.inhalife.board.dto.request.CreateArticleCommentRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleCommentResponse;
import com.capstonedesign.inhalife.board.service.ArticleCommentService;
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

@Tag(name = "게시글 댓글 API", description = "게시글 댓글 관련 API")
@RestController
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;
    private final UserService userService;
    private final ArticleService articleService;

    @Operation(summary = "게시글 댓글 달기 API", description = "게시글에 댓글을 작성합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "댓글을 작성할 유저의 id"),
            @Parameter(name = "articleId", description = "댓글을 작성할 게시글의 id" ),
            @Parameter(name = "contents", description = "댓글 내용" ),
    })
    @PostMapping("/article-comment")
    public ResponseEntity<Void> createArticleComment(
            @RequestBody @Valid CreateArticleCommentRequest request) {
        User user = userService.getById(request.getUserId());
        Article article = articleService.getById(request.getArticleId());

        ArticleComment articleComment = new ArticleComment(user, article, request.getContents());
        articleCommentService.createArticleComment(articleComment);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저가 작성한한 모든 댓글 조회 API", description = "유저가 작성한 모든 댓글을 조회합니다.")
    @Parameters({
            @Parameter(name = "userIndex", description = "작성한 댓글을 조회할 유저의 id"),
            @Parameter(name = "page", description = "작성한 댓글이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 댓글의 개수" )
    })
    @GetMapping("/user/{userIndex}/article-comment")
    public ResponseEntity<List<ReadArticleCommentResponse>> readUsersArticleComment(
            @PathVariable Long userIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        User user = userService.getById(userIndex);

        List<ReadArticleCommentResponse> articleCommentList = articleCommentService.getAllArticleCommentByUserId(userIndex, page, size);

        return ResponseEntity.ok(articleCommentList);
    }

    @Operation(summary = "게시글에 달린 모든 댓글 조회 API", description = "게시글에 달린 모든 댓글을 조회합니다.")
    @Parameters({
            @Parameter(name = "articleIndex", description = "작성된 댓글을 조회할 게시글의 id"),
            @Parameter(name = "page", description = "작성된 댓글이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 댓글의 개수" )
    })
    @GetMapping("/article/{articleIndex}/article-comment")
    public ResponseEntity<List<ReadArticleCommentResponse>> readAllArticleCommentAtArticle(
            @PathVariable Long articleIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Article article = articleService.getById(articleIndex);

        List<ReadArticleCommentResponse> articleCommentList = articleCommentService.getAllArticleCommentByArticleId(articleIndex, page, size);

        return ResponseEntity.ok(articleCommentList);
    }

    @Operation(summary = "댓글 삭제 API", description = "특정 댓글을 삭제합니다.")
    @Parameter(name = "articleCommentIndex", description = "삭제할 댓글의 id")
    @DeleteMapping("/article-comment/{articleCommentIndex}")
    public ResponseEntity<Void> deleteArticleComment(
            @PathVariable Long articleCommentIndex) {
        ArticleComment articleComment = articleCommentService.getById(articleCommentIndex);

        articleCommentService.deleteArticleComment(articleComment);

        return ResponseEntity.ok().build();
    }
}
