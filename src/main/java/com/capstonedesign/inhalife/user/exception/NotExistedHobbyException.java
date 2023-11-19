package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedHobbyException extends ServiceException {

    public NotExistedHobbyException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 취미입니다.");
    }
}
