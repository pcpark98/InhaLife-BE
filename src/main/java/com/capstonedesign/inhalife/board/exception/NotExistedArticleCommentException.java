package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedArticleCommentException extends ServiceException {

    public NotExistedArticleCommentException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다.");
    }
}
