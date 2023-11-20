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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleFavoriteController {

    private final ArticleFavoriteService articleFavoriteService;
    private final UserService userService;
    private final ArticleService articleService;

    @PostMapping("/article-favorite")
    public ResponseEntity<Void> createArticleFavorite(
            @RequestBody @Valid CreateArticleFavoriteRequest request) {
        User user = userService.getById(request.getUserId());
        Article article = articleService.getById(request.getArticleId());

        ArticleFavorite articleFavorite = new ArticleFavorite(user, article);
        articleFavoriteService.createArticleFavorite(articleFavorite);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userIndex}/article-favorite")
    public ResponseEntity<List<ReadFavoriteArticleResponse>> readUsersArticleFavorite(
            @PathVariable Long userIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        User user = userService.getById(userIndex);

        List<ReadFavoriteArticleResponse> favoriteArticleList = articleFavoriteService.getAllFavoriteArticleByUserId(userIndex, page, size);

        return ResponseEntity.ok(favoriteArticleList);
    }

    @DeleteMapping("/article-favorite/{articleFavoriteIndex}")
    public ResponseEntity<Void> deleteArticleFavorite(
            @PathVariable Long articleFavoriteIndex) {
        ArticleFavorite articleFavorite = articleFavoriteService.getById(articleFavoriteIndex);

        articleFavoriteService.deleteArticleFavorite(articleFavorite);

        return ResponseEntity.ok().build();
    }
}
