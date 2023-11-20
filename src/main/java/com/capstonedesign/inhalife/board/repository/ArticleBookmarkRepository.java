package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.ArticleBookmark;
import com.capstonedesign.inhalife.board.repository.jpa.ArticleBookmarkJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleBookmarkRepository {

    private final ArticleBookmarkJpaRepository articleBookmarkJpaRepository;

    public Long save(ArticleBookmark articleBookmark) {
        return articleBookmarkJpaRepository.save(articleBookmark).getId();
    }

    public Optional<ArticleBookmark> findById(Long id) {
        Optional<ArticleBookmark> articleBookmark = articleBookmarkJpaRepository.findById(id);
        return articleBookmark;
    }

    public List<ArticleBookmark> findAllByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return articleBookmarkJpaRepository.findAllByUser_Id(userId, pageRequest);
    }

    public List<ArticleBookmark> findAllByArticleId(Long articleId) {
        return articleBookmarkJpaRepository.findAllByArticle_Id(articleId);
    }

    public Optional<ArticleBookmark> findByUserIdAndArticleId(Long userId, Long articleId) {
        Optional<ArticleBookmark> articleBookmark = articleBookmarkJpaRepository.findByUser_IdAndArticle_Id(userId, articleId);
        return articleBookmark;
    }

    public void delete(Long id) {
        articleBookmarkJpaRepository.deleteById(id);
    }
}
