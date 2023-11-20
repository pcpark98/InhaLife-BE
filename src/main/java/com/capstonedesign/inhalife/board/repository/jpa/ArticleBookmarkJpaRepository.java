package com.capstonedesign.inhalife.board.repository.jpa;

import com.capstonedesign.inhalife.board.domain.ArticleBookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleBookmarkJpaRepository extends JpaRepository<ArticleBookmark, Long> {

    List<ArticleBookmark> findAllByUser_Id(Long userId, Pageable pageable);

    List<ArticleBookmark> findAllByArticle_Id(Long articleId);

    Optional<ArticleBookmark> findByUser_IdAndArticle_Id(Long userId, Long articleId);
}
