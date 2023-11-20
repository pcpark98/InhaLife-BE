package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.repository.jpa.ArticleImgJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleImgRepository {

    private final ArticleImgJpaRepository articleImgJpaRepository;

    public Long save(ArticleImg articleImg) {
        return articleImgJpaRepository.save(articleImg).getId();
    }

    public Optional<ArticleImg> findById(Long id) {
        Optional<ArticleImg> articleImg = articleImgJpaRepository.findById(id);
        return articleImg;
    }

    public List<ArticleImg> findAllByArticleId(Long articleId) {
        List<ArticleImg> articleImgList = articleImgJpaRepository.findAllByArticle_Id(articleId);
        return articleImgList;
    }

    public void delete(Long id) {
        articleImgJpaRepository.deleteById(id);
    }
}
