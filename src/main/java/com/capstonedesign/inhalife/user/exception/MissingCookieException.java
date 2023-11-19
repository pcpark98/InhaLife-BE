package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class MissingCookieException extends ServiceException {

    public MissingCookieException() {
        super(HttpStatus.BAD_REQUEST, "쿠키가 누락되었습니다.");
    }
}
