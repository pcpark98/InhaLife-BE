package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class ExpiredSessionException extends ServiceException {

    public ExpiredSessionException() {super(HttpStatus.UNAUTHORIZED, "만료된 세션입니다.");}
}
