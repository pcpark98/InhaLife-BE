package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.ArticleCommentFavorite;
import com.capstonedesign.inhalife.board.repository.jpa.ArticleCommentFavoriteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleCommentFavoriteRepository {

    private final ArticleCommentFavoriteJpaRepository articleCommentFavoriteJpaRepository;

    public Long save(ArticleCommentFavorite articleCommentFavorite) {
        return articleCommentFavoriteJpaRepository.save(articleCommentFavorite).getId();
    }

    public Optional<ArticleCommentFavorite> findById(Long id) {
        Optional<ArticleCommentFavorite> articleCommentFavorite = articleCommentFavoriteJpaRepository.findById(id);
        return articleCommentFavorite;
    }

    public List<ArticleCommentFavorite> findAllByArticleCommentId(Long articleCommentId) {
        return articleCommentFavoriteJpaRepository.findAllByArticleComment_Id(articleCommentId);
    }

    public Optional<ArticleCommentFavorite> findByUserIdAndArticleCommentId(Long userId, Long articleCommentId) {
        Optional<ArticleCommentFavorite> articleCommentFavorite = articleCommentFavoriteJpaRepository.findByUser_IdAndArticleComment_Id(userId, articleCommentId);
        return articleCommentFavorite;
    }

    public void delete(Long id) {
        articleCommentFavoriteJpaRepository.deleteById(id);
    }
}
