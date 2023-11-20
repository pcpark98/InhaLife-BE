package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedArticleFavoriteException extends ServiceException {

    public NotExistedArticleFavoriteException() {
        super(HttpStatus.NOT_FOUND, "좋아요 하지 않은 게시글입니다.");
    }
}
