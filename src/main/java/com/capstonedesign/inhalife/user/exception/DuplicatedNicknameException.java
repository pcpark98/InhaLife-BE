package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedNicknameException extends ServiceException {

    public DuplicatedNicknameException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다.");
    }
}
