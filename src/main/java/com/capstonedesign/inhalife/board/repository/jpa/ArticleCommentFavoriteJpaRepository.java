package com.capstonedesign.inhalife.board.repository.jpa;

import com.capstonedesign.inhalife.board.domain.ArticleCommentFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentFavoriteJpaRepository extends JpaRepository<ArticleCommentFavorite, Long> {

    List<ArticleCommentFavorite> findAllByArticleComment_Id(Long articleCommentId);

    Optional<ArticleCommentFavorite> findByUser_IdAndArticleComment_Id(Long userId, Long articleCommentId);
}
