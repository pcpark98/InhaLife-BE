package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.ArticleComment;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleCommentResponse;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleCommentException;
import com.capstonedesign.inhalife.board.repository.ArticleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;

    @Transactional
    public Long createArticleComment(ArticleComment articleComment) {
        return articleCommentRepository.save(articleComment);
    }

    public ArticleComment getById(Long id) {
        if(id == null) throw new NotExistedArticleCommentException();

        Optional<ArticleComment> articleComment = articleCommentRepository.findById(id);
        if(!articleComment.isPresent()) throw new NotExistedArticleCommentException();

        return articleComment.get();
    }

    public List<ReadArticleCommentResponse> getAllArticleCommentByUserId(Long userId, int page, int size) {
        List<ReadArticleCommentResponse> responseList = new ArrayList<>();

        List<ArticleComment> articleCommentList = articleCommentRepository.findAllByUserId(userId, page, size);
        articleCommentList.forEach(articleComment -> {
            responseList.add(
                    new ReadArticleCommentResponse(
                            articleComment.getId(),
                            articleComment.getUser().getId(),
                            articleComment.getArticle().getId(),
                            articleComment.getContents(),
                            articleComment.getCreatedAt()
                    )
            );
        });

        return responseList;
    }

    public List<ReadArticleCommentResponse> getAllArticleCommentByArticleId(Long articleId, int page, int size) {
        List<ReadArticleCommentResponse> responseList = new ArrayList<>();

        List<ArticleComment> articleCommentList = articleCommentRepository.findAllByArticleId(articleId, page, size);
        articleCommentList.forEach(articleComment -> {
            responseList.add(
                    new ReadArticleCommentResponse(
                            articleComment.getId(),
                            articleComment.getUser().getId(),
                            articleComment.getArticle().getId(),
                            articleComment.getContents(),
                            articleComment.getCreatedAt()
                    )
            );
        });

        return responseList;
    }

    @Transactional
    public void deleteArticleComment(ArticleComment articleComment) {
        articleCommentRepository.delete(articleComment.getId());
    }
}
