package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.repository.jpa.ArticleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final ArticleJpaRepository articleJpaRepository;

    public Long save(Article article) {
        return articleJpaRepository.save(article).getId();
    }

    public Optional<Article> findById(Long id) {
        Optional<Article> article = articleJpaRepository.findById(id);
        return article;
    }

    public List<Article> findAllByUserIndex(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return articleJpaRepository.findAllByUser_Id(userId, pageRequest);
    }

    public List<Article> findAllByBoardIndex(Long boardId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return articleJpaRepository.findAllByBoard_Id(boardId, pageRequest);
    }

    public void delete(Long id) {
        articleJpaRepository.deleteById(id);
    }
}
