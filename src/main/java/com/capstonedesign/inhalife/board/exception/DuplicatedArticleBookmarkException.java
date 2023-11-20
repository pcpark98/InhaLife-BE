package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedArticleBookmarkException extends ServiceException {

    public DuplicatedArticleBookmarkException() {
        super(HttpStatus.CONFLICT, "이미 북마크한 게시글입니다.");
    }
}
