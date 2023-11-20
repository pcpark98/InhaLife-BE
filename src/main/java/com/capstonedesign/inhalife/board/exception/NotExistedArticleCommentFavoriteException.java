package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedArticleCommentFavoriteException extends ServiceException {

    public NotExistedArticleCommentFavoriteException() {
        super(HttpStatus.NOT_FOUND, "좋아요하지 않은 댓글입니다.");
    }
}
