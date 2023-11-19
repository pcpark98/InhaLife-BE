package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedHobbyOfUserException extends ServiceException {

    public DuplicatedHobbyOfUserException() {
        super(HttpStatus.CONFLICT, "이미 지정한 취미입니다.");
    }
}
