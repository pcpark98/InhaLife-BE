package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.Board;
import com.capstonedesign.inhalife.board.dto.request.CreateArticleRequest;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleResponse;
import com.capstonedesign.inhalife.board.service.ArticleFavoriteService;
import com.capstonedesign.inhalife.board.service.ArticleImgService;
import com.capstonedesign.inhalife.board.service.ArticleService;
import com.capstonedesign.inhalife.board.service.BoardService;
import com.capstonedesign.inhalife.user.domain.User;
import com.capstonedesign.inhalife.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "게시글 API", description = "게시글 관련 API")
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final BoardService boardService;
    private final ArticleImgService articleImgService;
    private final ArticleFavoriteService articleFavoriteService;


    @Operation(summary = "게시글 등록 API", description = "게시판에 게시글을 등록합니다.")
    @Parameters({
            @Parameter(name = "boardIndex", description = "게시글을 등록할 게시판의 id"),
            @Parameter(name = "userId", description = "게시글을 작성할 유저의 id" ),
            @Parameter(name = "title", description = "작성할 게시글의 제목" ),
            @Parameter(name = "contents", description = "작성할 게시글의 내용" ),
            @Parameter(name = "images", description = "게시글에 업로드할 이미지 리스트" ),
    })
    @PostMapping(value = "/board/{boardIndex}/article", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> createArticle(
            @PathVariable Long boardIndex,
            @RequestPart(value = "request") @Valid CreateArticleRequest request,
            @RequestPart(value = "images", required = false)List<MultipartFile> images) {
        User user = userService.getById(request.getUserId());
        Board board = boardService.getById(boardIndex);

        Article article = new Article(user, board, request.getTitle(), request.getContents());

        articleService.createArticle(article, images);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시판의 모든 게시글 조회 API", description = "특정 게시판의 모든 게시글을 조회합니다.")
    @Parameters({
            @Parameter(name = "boardIndex", description = "게시글을 조회할 게시판의 id"),
            @Parameter(name = "page", description = "조회할 게시글이 위치한 페이지" ),
            @Parameter(name = "size", description = "한 페이지에 조회할 게시글의 개수" )
    })
    @GetMapping("/board/{boardIndex}/article")
    public ResponseEntity<List<ReadArticleResponse>> readArticleList (
            @PathVariable Long boardIndex,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReadArticleResponse> articleList = articleService.getArticleByBoardId(boardIndex, page, size);

        return ResponseEntity.ok(articleList);
    }


    @Operation(summary = "단일 게시글 조회 API", description = "특정 게시글을 조회합니다.")
    @Parameters({
            @Parameter(name = "userIndex", description = "게시글을 조회하는 유저의 id", in = ParameterIn.HEADER),
            @Parameter(name = "articleIndex", description = "조회할 게시글의 id" )
    })
    @GetMapping("/article/{articleIndex}")
    public ResponseEntity<ReadArticleResponse> readArticleById (
            @RequestHeader("userIndex") Long userId,
            @PathVariable Long articleIndex){
        User user = userService.getById(userId);
        Article article = articleService.getById(articleIndex);

        List<String> imgUrlList = articleImgService.getAllUrlByArticleId(article.getId());

        return ResponseEntity.ok(
                new ReadArticleResponse(
                        article.getId(),
                        article.getUser().getId(),
                        article.getUser().getNickname(),
                        article.getBoard().getId(),
                        article.getBoard().getName(),
                        article.getTitle(),
                        article.getContents(),
                        imgUrlList,
                        article.getCreatedAt(),
                        articleService.isWrittenBy(user, article),
                        articleFavoriteService.getFavoriteCountOfArticle(article.getId())
                )
        );
    }

    @Operation(summary = "게시글 삭제 API", description = "특정 게시글을 삭제합니다.")
    @Parameter(name = "articleIndex", description = "삭제할 게시글의 id")
    @DeleteMapping("/article/{articleIndex}")
    public ResponseEntity<Void> deleteArticle(
            @PathVariable Long articleIndex) {
        Article article = articleService.getById(articleIndex);

        articleService.deleteArticle(article);

        return ResponseEntity.ok().build();
    }
}
