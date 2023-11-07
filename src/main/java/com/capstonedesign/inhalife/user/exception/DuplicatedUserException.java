package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedUserException extends ServiceException {

    public DuplicatedUserException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 유저입니다.");
    }
}
