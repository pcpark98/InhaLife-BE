package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedBoardBookmarkException extends ServiceException {

    public DuplicatedBoardBookmarkException() {
        super(HttpStatus.CONFLICT, "이미 즐겨찾기한 게시판입니다.");
    }
}
