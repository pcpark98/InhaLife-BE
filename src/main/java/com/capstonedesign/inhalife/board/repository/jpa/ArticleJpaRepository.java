package com.capstonedesign.inhalife.board.repository.jpa;

import com.capstonedesign.inhalife.board.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleJpaRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByUser_Id(Long userId, Pageable pageable);

    List<Article> findAllByBoard_Id(Long boardId, Pageable pageable);
}
