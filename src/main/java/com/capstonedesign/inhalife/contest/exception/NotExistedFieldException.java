package com.capstonedesign.inhalife.contest.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedFieldException extends ServiceException {

    public NotExistedFieldException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 분야입니다.");
    }
}
