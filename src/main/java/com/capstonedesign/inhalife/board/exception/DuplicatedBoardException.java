package com.capstonedesign.inhalife.board.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedBoardException extends ServiceException {

    public DuplicatedBoardException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 게시판입니다.");
    }
}
