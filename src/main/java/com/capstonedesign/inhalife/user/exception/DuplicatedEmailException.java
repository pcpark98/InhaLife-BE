package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedEmailException extends ServiceException {

    public DuplicatedEmailException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");
    }
}
