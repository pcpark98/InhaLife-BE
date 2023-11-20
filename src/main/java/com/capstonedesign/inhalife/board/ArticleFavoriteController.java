package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleFavorite;
import com.capstonedesign.inhalife.board.dto.request.CreateArticleFavoriteRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleResponse;
import com.capstonedesign.inhalife.board.dto.response.ReadFavoriteArticleResponse;
import com.capstonedesign.inhalife.board.service.ArticleFavoriteService;
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

@Tag(name = "게시글 좋아요 API", description = "게시글 좋아요 관련 API")
@RestController
@RequiredArgsConstructor
public class ArticleFavoriteController {

    private final ArticleFavoriteService articleFavoriteService;
    private final UserService userService;
    private final ArticleService articleService;

    @Operation(summary = "게시글 좋아요 누르기 API", description = "게시글을 좋아하는 게시글로 등록합니다.")
    @Parameters({
            @Parameter(name = "userId", description = "좋아요를 누를 유저의 id"),
            @Parameter(name = "articleId", description = "좋아하는 게시글로 저장할 게시글의 id" )
    })
    @PostMapping("/article-favorite")
    public ResponseEntity<Void> createArticleFavorite(
            @RequestBody @Valid CreateArticleFavoriteRequest request) {
        User user = userService.getById(request.getUserId());
        Article article = articleService.getById(request.getArticleId());

        ArticleFavorite articleFavorite = new ArticleFavorite(user, article);
        articleFavoriteService.createArticleFavorite(articleFavorite);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저가 좋아하는 모든 게시글 조회 API", description = "유저가 좋아요를 누른 모든 게시글을 조회합니다.")
    @Parameters({
            @Parameter(name = "userIndex", description = "좋아하는 게시글을 조회할 유저의 id"),
            @Parameter(name = "page", description = "조회할 게시글이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 게시글의 개수" )
    })
    @GetMapping("/user/{userIndex}/article-favorite")
    public ResponseEntity<List<ReadFavoriteArticleResponse>> readUsersArticleFavorite(
            @PathVariable Long userIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        User user = userService.getById(userIndex);

        List<ReadFavoriteArticleResponse> favoriteArticleList = articleFavoriteService.getAllFavoriteArticleByUserId(userIndex, page, size);

        return ResponseEntity.ok(favoriteArticleList);
    }

    @Operation(summary = "게시글 좋아요 취소 API", description = "유저가 좋아요 했던 게시글의 좋아요를 취소합니다.")
    @Parameter(name = "articleFavoriteIndex", description = "좋아요 했던 게시글의 좋아요 id")
    @DeleteMapping("/article-favorite/{articleFavoriteIndex}")
    public ResponseEntity<Void> deleteArticleFavorite(
            @PathVariable Long articleFavoriteIndex) {
        ArticleFavorite articleFavorite = articleFavoriteService.getById(articleFavoriteIndex);

        articleFavoriteService.deleteArticleFavorite(articleFavorite);

        return ResponseEntity.ok().build();
    }
}
