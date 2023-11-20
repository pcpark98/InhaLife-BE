package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleFavorite;
import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleResponse;
import com.capstonedesign.inhalife.board.dto.response.ReadFavoriteArticleResponse;
import com.capstonedesign.inhalife.board.exception.DuplicatedArticleFavoriteException;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleFavoriteException;
import com.capstonedesign.inhalife.board.repository.ArticleFavoriteRepository;
import com.capstonedesign.inhalife.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleFavoriteService {

    private final ArticleFavoriteRepository articleFavoriteRepository;

    @Transactional
    public Long createArticleFavorite(ArticleFavorite articleFavorite) {
        Optional<ArticleFavorite> duplicatedArticleFavorite = articleFavoriteRepository.findByUserIdAndArticleId(articleFavorite.getUser().getId(), articleFavorite.getArticle().getId());
        if(duplicatedArticleFavorite.isPresent()) throw new DuplicatedArticleFavoriteException();

        return articleFavoriteRepository.save(articleFavorite);
    }

    public ArticleFavorite getById(Long id) {
        if(id == null) throw new NotExistedArticleFavoriteException();

        Optional<ArticleFavorite> articleFavorite = articleFavoriteRepository.findById(id);
        if(!articleFavorite.isPresent()) throw new NotExistedArticleFavoriteException();

        return articleFavorite.get();
    }

    public List<ReadFavoriteArticleResponse> getAllFavoriteArticleByUserId(Long userId, int page, int size) {
        List<ReadFavoriteArticleResponse> articleList = new ArrayList<>();

        List<ArticleFavorite> articleFavoriteList = articleFavoriteRepository.findAllByUserId(userId, page, size);
        articleFavoriteList.forEach(articleFavorite -> {
            List<String> articleImgUrlList = new ArrayList<>();

            List<ArticleImg> articleImgList = articleFavorite.getArticle().getArticleImgs();
            articleImgList.forEach(articleImg -> {
                articleImgUrlList.add(articleImg.getImgUrl());
            });

            articleList.add(
                    new ReadFavoriteArticleResponse(
                            articleFavorite.getId(),
                            articleFavorite.getArticle().getId(),
                            articleFavorite.getUser().getId(),
                            articleFavorite.getUser().getNickname(),
                            articleFavorite.getArticle().getBoard().getId(),
                            articleFavorite.getArticle().getBoard().getName(),
                            articleFavorite.getArticle().getTitle(),
                            articleFavorite.getArticle().getContents(),
                            articleImgUrlList,
                            articleFavorite.getArticle().getCreatedAt()
                    )
            );
        });

        return articleList;
    }

    public int getFavoriteCountOfArticle(Long articleId) {
        return articleFavoriteRepository.findAllByArticleId(articleId).size();
    }

    public boolean isFavorite(User user, Article article) {
        Optional<ArticleFavorite> articleFavorite = articleFavoriteRepository.findByUserIdAndArticleId(user.getId(), article.getId());
        if(articleFavorite.isPresent()) return true;
        else return false;
    }

    @Transactional
    public void deleteArticleFavorite(ArticleFavorite articleFavorite) {
        articleFavoriteRepository.delete(articleFavorite.getId());
    }
}
