package com.capstonedesign.inhalife.board.repository.jpa;

import com.capstonedesign.inhalife.board.domain.ArticleImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleImgJpaRepository extends JpaRepository<ArticleImg, Long> {

    List<ArticleImg> findAllByArticle_Id(Long articleId);
}
