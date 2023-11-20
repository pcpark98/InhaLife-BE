package com.capstonedesign.inhalife.board.repository;

import com.capstonedesign.inhalife.board.domain.ArticleComment;
import com.capstonedesign.inhalife.board.repository.jpa.ArticleCommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleCommentRepository {

    private final ArticleCommentJpaRepository articleCommentJpaRepository;

    public Long save(ArticleComment articleComment) {
        return articleCommentJpaRepository.save(articleComment).getId();
    }

    public Optional<ArticleComment> findById(Long id) {
        Optional<ArticleComment> articleComment = articleCommentJpaRepository.findById(id);
        return articleComment;
    }

    public List<ArticleComment> findAllByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return articleCommentJpaRepository.findAllByUser_Id(userId, pageRequest);
    }

    public List<ArticleComment> findAllByArticleId(Long articleId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return articleCommentJpaRepository.findAllByArticle_Id(articleId, pageRequest);
    }

    public void delete(Long id) {
        articleCommentJpaRepository.deleteById(id);
    }
}
