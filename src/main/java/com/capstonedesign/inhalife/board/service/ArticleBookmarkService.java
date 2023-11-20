package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleBookmark;
import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleBookmarkResponse;
import com.capstonedesign.inhalife.board.exception.DuplicatedArticleBookmarkException;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleBookmarkException;
import com.capstonedesign.inhalife.board.repository.ArticleBookmarkRepository;
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
public class ArticleBookmarkService {

    private final ArticleBookmarkRepository articleBookmarkRepository;

    @Transactional
    public Long createArticleBookmark(ArticleBookmark articleBookmark) {
        Optional<ArticleBookmark> duplicatedArticleBookmark = articleBookmarkRepository.findByUserIdAndArticleId(articleBookmark.getUser().getId(), articleBookmark.getArticle().getId());
        if(duplicatedArticleBookmark.isPresent()) throw new DuplicatedArticleBookmarkException();

        return articleBookmarkRepository.save(articleBookmark);
    }

    public ArticleBookmark getById(Long id) {
        if(id == null) throw new NotExistedArticleBookmarkException();

        Optional<ArticleBookmark> articleBookmark = articleBookmarkRepository.findById(id);
        if(!articleBookmark.isPresent()) throw new NotExistedArticleBookmarkException();

        return articleBookmark.get();
    }

    public List<ReadArticleBookmarkResponse> getAllArticleBookmarkByUserId(Long userId, int page, int size) {
        List<ReadArticleBookmarkResponse> articleList = new ArrayList<>();

        List<ArticleBookmark> articleBookmarkList = articleBookmarkRepository.findAllByUserId(userId, page, size);
        articleBookmarkList.forEach(articleBookmark -> {
            List<String> articleImgUrlList = new ArrayList<>();

            List<ArticleImg> articleImgList = articleBookmark.getArticle().getArticleImgs();
            articleImgList.forEach(articleImg -> {
                articleImgUrlList.add(articleImg.getImgUrl());
            });

            articleList.add(
                    new ReadArticleBookmarkResponse(
                            articleBookmark.getId(),
                            articleBookmark.getArticle().getId(),
                            articleBookmark.getUser().getId(),
                            articleBookmark.getUser().getNickname(),
                            articleBookmark.getArticle().getBoard().getId(),
                            articleBookmark.getArticle().getBoard().getName(),
                            articleBookmark.getArticle().getTitle(),
                            articleBookmark.getArticle().getContents(),
                            articleImgUrlList,
                            articleBookmark.getArticle().getCreatedAt()
                    )
            );
        });

        return articleList;
    }

    public int getArticleBookmarkCountOfArticle(Long articleId) {
        return articleBookmarkRepository.findAllByArticleId(articleId).size();
    }

    public boolean isBookmark(User user, Article article) {
        Optional<ArticleBookmark> articleBookmark = articleBookmarkRepository.findByUserIdAndArticleId(user.getId(), article.getId());
        if(articleBookmark.isPresent()) return true;
        else return false;
    }

    @Transactional
    public void deleteArticleBookmark(ArticleBookmark articleBookmark) {
        articleBookmarkRepository.delete(articleBookmark.getId());
    }
}
