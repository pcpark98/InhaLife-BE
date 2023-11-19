package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedBoardException extends ServiceException {

    public NotExistedBoardException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다.");
    }
}
