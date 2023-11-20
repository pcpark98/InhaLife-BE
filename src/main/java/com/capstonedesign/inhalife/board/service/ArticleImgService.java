package com.capstonedesign.inhalife.board.service;

import com.capstonedesign.inhalife.board.domain.ArticleImg;
import com.capstonedesign.inhalife.board.exception.NotExistedArticleImgException;
import com.capstonedesign.inhalife.board.repository.ArticleImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleImgService {

    private final ArticleImgRepository articleImgRepository;

    public ArticleImg getById(Long id) {
        if(id == null) throw new NotExistedArticleImgException();

        Optional<ArticleImg> articleImg = articleImgRepository.findById(id);
        if(!articleImg.isPresent()) throw new NotExistedArticleImgException();

        return articleImg.get();
    }

    public List<String> getAllUrlByArticleId(Long articleId) {
        List<String> imgUrlList = new ArrayList<>();

        List<ArticleImg> articleImgList = articleImgRepository.findAllByArticleId(articleId);
        articleImgList.forEach(articleImg -> {
            imgUrlList.add(articleImg.getImgUrl());
        });

        return imgUrlList;
    }

    @Transactional
    public void deleteArticleImg(ArticleImg articleImg) {
        articleImgRepository.delete(articleImg.getId());
    }
}
