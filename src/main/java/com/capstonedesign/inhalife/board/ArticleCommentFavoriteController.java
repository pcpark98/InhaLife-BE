package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.ArticleComment;
import com.capstonedesign.inhalife.board.domain.ArticleCommentFavorite;
import com.capstonedesign.inhalife.board.dto.request.CreateArticleCommentFavoriteRequest;
import com.capstonedesign.inhalife.board.service.ArticleCommentFavoriteService;
import com.capstonedesign.inhalife.board.service.ArticleCommentService;
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

@Tag(name = "게시글 댓글 좋아요 API", description = "게시글 댓글 좋아요 관련 API")
@RestController
@RequiredArgsConstructor
public class ArticleCommentFavoriteController {

    private final ArticleCommentFavoriteService articleCommentFavoriteService;
    private final UserService userService;
    private final ArticleCommentService articleCommentService;

    @Operation(summary = "게시글 댓글 좋아요 누르기 API", description = "댓글을 좋아하는 댓글로 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "좋아요를 누를 유저의 id"),
            @Parameter(name = "articleCommentId", description = "좋아하는 댓글로 저장할 댓글의 id" )
    })
    @PostMapping("/article-comment-favorite")
    public ResponseEntity<Void> createArticleCommentFavorite(
            @RequestBody @Valid CreateArticleCommentFavoriteRequest request) {
        User user = userService.getById(request.getUserId());
        ArticleComment articleComment = articleCommentService.getById(request.getArticleCommentId());

        ArticleCommentFavorite articleCommentFavorite = new ArticleCommentFavorite(user, articleComment);
        articleCommentFavoriteService.createArticleCommentFavorite(articleCommentFavorite);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/article-comment-favorite/{articleCommentFavoriteIndex}")
    public ResponseEntity<Void> deleteArticleCommentFavorite(
            @PathVariable Long articleCommentFavoriteIndex) {
        ArticleCommentFavorite articleCommentFavorite = articleCommentFavoriteService.getById(articleCommentFavoriteIndex);

        articleCommentFavoriteService.deleteArticleCommentFavorite(articleCommentFavorite);

        return ResponseEntity.ok().build();
    }
}
