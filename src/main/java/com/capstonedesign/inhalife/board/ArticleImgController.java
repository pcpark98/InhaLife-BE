package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.service.ArticleImgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 이미지 API", description = "게시글의 이미지 관련 API")
@RestController
@RequiredArgsConstructor
public class ArticleImgController {

    private final ArticleImgService articleImgService;

    @Operation(summary = "게시글의 이미지 삭제 API", description = "게시글에 업로드된 이미지를 삭제합니다.")
    @Parameter(name = "articleImgIndex", description = "삭제할 이미지의 id")
    @DeleteMapping("/article-img/{articleImgIndex}")
    public ResponseEntity<Void> deleteArticleImg(
            @PathVariable Long articleImgIndex) {
        ArticleImg articleImg = articleImgService.getById(articleImgIndex);

        articleImgService.deleteArticleImg(articleImg);

        return ResponseEntity.ok().build();
    }
}
