package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.Article;
import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.dto.response.ReadArticleResponse;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleException;
import com.capstonedesign.inhalife.board.repository.ArticleFavoriteRepository;
import com.capstonedesign.inhalife.board.repository.ArticleRepository;
import com.capstonedesign.inhalife.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleFavoriteRepository articleFavoriteRepository;

    @Transactional
    public Long createArticle(Article article) {

        Long savedArticleId = articleRepository.save(article);

        // TODO: S3에 이미지 업로드하는 로직 작성

        return savedArticleId;
    }

    public Article getById(Long id) {
        if(id == null) throw new NotExistedArticleException();

        Optional<Article> article = articleRepository.findById(id);
        if(!article.isPresent()) throw new NotExistedArticleException();

        return article.get();
    }

    /*
    public List<ReadArticleResponse> getArticleByUserId(Long userId, int page, int size) {
        List<ReadArticleResponse> responseList = new ArrayList<>();

        List<Article> articleList = articleRepository.findAllByUserIndex(userId, page, size);
        articleList.forEach(article -> {
            List<String> articleImgUrlList = new ArrayList<>();

            List<ArticleImg> articleImgList = article.getArticleImgs();
            articleImgList.forEach(articleImg -> {
                articleImgUrlList.add(articleImg.getImgUrl());
            });

            responseList.add(
                    new ReadArticleResponse(
                            article.getId(),
                            article.getUser().getId(),
                            article.getUser().getNickname(),
                            article.getBoard().getId(),
                            article.getBoard().getName(),
                            article.getTitle(),
                            article.getContents(),
                            articleImgUrlList,
                            article.getCreatedAt(),
                            true,
                            articleFavoriteRepository.findAllByArticleId(article.getId()).size()
                    )
            );
        });

        return responseList;
    }

     */

    public List<ReadArticleResponse> getArticleByBoardId(Long boardId, int page, int size) {
        List<ReadArticleResponse> responseList = new ArrayList<>();

        List<Article> articleList = articleRepository.findAllByBoardIndex(boardId, page, size);
        articleList.forEach(article -> {
            List<String> articleImgUrlList = new ArrayList<>();

            List<ArticleImg> articleImgList = article.getArticleImgs();
            articleImgList.forEach(articleImg -> {
                articleImgUrlList.add(articleImg.getImgUrl());
            });

            java.time.LocalDate localDate = article.getCreatedAt().toLocalDate();

            // 출력 형식 지정
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // 날짜를 문자열로 변환하여 출력
            String formattedDate = localDate.format(formatter);



            responseList.add(
                    new ReadArticleResponse(
                            article.getId(),
                            article.getUser().getId(),
                            article.getUser().getNickname(),
                            article.getBoard().getId(),
                            article.getBoard().getName(),
                            article.getTitle(),
                            article.getContents(),
                            articleImgUrlList,
                            formattedDate,
                            true,
                            articleFavoriteRepository.findAllByArticleId(article.getId()).size()
                    )
            );
        });

        return responseList;
    }

    public boolean isWrittenBy(User user, Article article) {
        if(article.getUser().getId().equals(user.getId())) return true;
        else return false;
    }

    @Transactional
    public void deleteArticle(Article article) {
        articleRepository.delete(article.getId());
    }
}
