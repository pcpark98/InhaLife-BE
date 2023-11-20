package com.capstonedesign.inhalife.board.repository.jpa;

import com.capstonedesign.inhalife.board.domain.ArticleFavorite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleFavoriteJpaRepository extends JpaRepository<ArticleFavorite, Long> {

    List<ArticleFavorite> findAllByUser_Id(Long userId, Pageable pageable);

    List<ArticleFavorite> findAllByArticle_Id(Long articleId);

    Optional<ArticleFavorite> findByUser_IdAndArticle_Id(Long userId, Long articleId);
}
