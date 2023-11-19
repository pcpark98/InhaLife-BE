package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedHobbyOfUserException extends ServiceException {

    public NotExistedHobbyOfUserException() {
        super(HttpStatus.NOT_FOUND, "지정되지 않은 취미입니다.");
    }
}
