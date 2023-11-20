package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.ArticleFavorite;
import com.capstonedesign.inhalife.board.repository.jpa.ArticleFavoriteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleFavoriteRepository {

    private final ArticleFavoriteJpaRepository articleFavoriteJpaRepository;

    public Long save(ArticleFavorite articleFavorite) {
        return articleFavoriteJpaRepository.save(articleFavorite).getId();
    }

    public Optional<ArticleFavorite> findById(Long id) {
        Optional<ArticleFavorite> articleFavorite = articleFavoriteJpaRepository.findById(id);
        return articleFavorite;
    }

    public List<ArticleFavorite> findAllByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return articleFavoriteJpaRepository.findAllByUser_Id(userId, pageRequest);
    }

    public List<ArticleFavorite> findAllByArticleId(Long articleId) {
        return articleFavoriteJpaRepository.findAllByArticle_Id(articleId);
    }

    public Optional<ArticleFavorite> findByUserIdAndArticleId(Long userId, Long articleId) {
        Optional<ArticleFavorite> articleFavorite = articleFavoriteJpaRepository.findByUser_IdAndArticle_Id(userId, articleId);
        return articleFavorite;
    }

    public void delete(Long id) {
        articleFavoriteJpaRepository.deleteById(id);
    }
}
