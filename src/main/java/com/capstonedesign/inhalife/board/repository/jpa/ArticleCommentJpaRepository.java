package com.capstonedesign.inhalife.board.repository.jpa;

import com.capstonedesign.inhalife.board.domain.ArticleComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleCommentJpaRepository extends JpaRepository<ArticleComment, Long> {

    List<ArticleComment> findAllByUser_Id(Long userId, Pageable pageable);

    List<ArticleComment> findAllByArticle_Id(Long userId, Pageable pageable);
}
