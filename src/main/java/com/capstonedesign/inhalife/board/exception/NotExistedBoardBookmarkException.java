package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedBoardBookmarkException extends ServiceException {

    public NotExistedBoardBookmarkException() {
        super(HttpStatus.NOT_FOUND, "즐겨찾기 하지 않은 게시판입니다.");
    }
}
