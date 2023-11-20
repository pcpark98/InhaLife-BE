package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedArticleImgException extends ServiceException {

    public NotExistedArticleImgException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 이미지입니다.");
    }
}
