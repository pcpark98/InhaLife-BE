package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedUserException extends ServiceException {

    public NotExistedUserException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다.");
    }
}
