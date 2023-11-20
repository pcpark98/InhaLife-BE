package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedArticleCommentFavoriteException extends ServiceException {

    public DuplicatedArticleCommentFavoriteException() {
        super(HttpStatus.CONFLICT, "이미 좋아요한 댓글입니다.");
    }
}
