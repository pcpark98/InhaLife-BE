package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedArticleFavoriteException extends ServiceException {

    public DuplicatedArticleFavoriteException() {
        super(HttpStatus.CONFLICT, "이미 좋아요한 게시글입니다.");
    }
}
