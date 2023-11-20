package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.ArticleComment;
import com.capstonedesign.inhalife.board.domain.ArticleCommentFavorite;
import com.capstonedesign.inhalife.board.exception.DuplicatedArticleCommentFavoriteException;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleCommentFavoriteException;
import com.capstonedesign.inhalife.board.repository.ArticleCommentFavoriteRepository;
import com.capstonedesign.inhalife.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleCommentFavoriteService {

    private final ArticleCommentFavoriteRepository articleCommentFavoriteRepository;

    @Transactional
    public Long createArticleCommentFavorite(ArticleCommentFavorite articleCommentFavorite) {
        Optional<ArticleCommentFavorite> duplicatedArticleCommentFavorite = articleCommentFavoriteRepository.findByUserIdAndArticleCommentId(articleCommentFavorite.getUser().getId(), articleCommentFavorite.getArticleComment().getId());
        if(duplicatedArticleCommentFavorite.isPresent()) throw new DuplicatedArticleCommentFavoriteException();

        return articleCommentFavoriteRepository.save(articleCommentFavorite);
    }

    public ArticleCommentFavorite getById(Long id) {
        if(id == null) throw new NotExistedArticleCommentFavoriteException();

        Optional<ArticleCommentFavorite> articleCommentFavorite = articleCommentFavoriteRepository.findById(id);
        if(!articleCommentFavorite.isPresent()) throw new NotExistedArticleCommentFavoriteException();

        return articleCommentFavorite.get();
    }

    public int getFavoriteCountOfArticleComment(Long articleCommentId) {
        return articleCommentFavoriteRepository.findAllByArticleCommentId(articleCommentId).size();
    }

    public boolean isFavorite(User user, ArticleComment articleComment) {
        Optional<ArticleCommentFavorite> articleCommentFavorite = articleCommentFavoriteRepository.findByUserIdAndArticleCommentId(user.getId(), articleComment.getId());
        if(articleCommentFavorite.isPresent()) return true;
        else return false;
    }

    @Transactional
    public void deleteArticleCommentFavorite(ArticleCommentFavorite articleCommentFavorite) {
        articleCommentFavoriteRepository.delete(articleCommentFavorite.getId());
    }
}
