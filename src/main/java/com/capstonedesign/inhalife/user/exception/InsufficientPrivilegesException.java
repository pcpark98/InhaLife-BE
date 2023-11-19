package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class InsufficientPrivilegesException extends ServiceException {

    public InsufficientPrivilegesException() {
        super(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
    }
}
