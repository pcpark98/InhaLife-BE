package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.dto.request.CreateArticleRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleResponse;
import com.capstonedesign.inhalife.board.service.ArticleService;
import com.capstonedesign.inhalife.board.service.BoardService;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final BoardService boardService;

    @PostMapping("/board/{boardIndex}/article")
    public ResponseEntity<Void> createArticle(
            @PathVariable Long boardIndex,
            @RequestBody @Valid CreateArticleRequest request) {
        User user = userService.getById(request.getUserId());
        Board board = boardService.getById(boardIndex);

        Article article = new Article(user, board, request.getTitle(), request.getContents());

        articleService.createArticle(article);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/board/{boardIndex}/article")
    public ResponseEntity<List<ReadArticleResponse>> readArticleList (
            @PathVariable Long boardIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadArticleResponse> articleList = articleService.getArticleByBoardId(boardIndex, page, size);

        return ResponseEntity.ok(articleList);
    }


    //TODO: 이미지 URL 리스트 가져오는 부분 서비스 코드로 변경
    @GetMapping("/article/{articleIndex}")
    public ResponseEntity<ReadArticleResponse> readArticleById (
            @RequestHeader("userIndex") Long userId,
            @PathVariable Long articleIndex){
        User user = userService.getById(userId);
        Article article = articleService.getById(articleIndex);

        List<String> tempImgUrl = new ArrayList<>();

        return ResponseEntity.ok(
                new ReadArticleResponse(
                        article.getId(),
                        article.getUser().getId(),
                        article.getUser().getNickname(),
                        article.getBoard().getId(),
                        article.getBoard().getName(),
                        article.getTitle(),
                        article.getContents(),
                        tempImgUrl,
                        article.getCreatedAt(),
                        articleService.isWrittenBy(user, article)
                )
        );
    }

    @DeleteMapping("/article/{articleIndex}")
    public ResponseEntity<Void> deleteArticle(
            @RequestParam Long articleIndex) {
        Article article = articleService.getById(articleIndex);

        articleService.deleteArticle(article);

        return ResponseEntity.ok().build();
    }
}
