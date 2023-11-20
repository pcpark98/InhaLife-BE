package com.capstonedesign.inhalife.board;

import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.service.ArticleImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleImgController {

    private final ArticleImgService articleImgService;

    @DeleteMapping("/article-img/{articleImgIndex}")
    public ResponseEntity<Void> deleteArticleImg(
            @PathVariable Long articleImgIndex) {
        ArticleImg articleImg = articleImgService.getById(articleImgIndex);

        articleImgService.deleteArticleImg(articleImg);

        return ResponseEntity.ok().build();
    }
}
