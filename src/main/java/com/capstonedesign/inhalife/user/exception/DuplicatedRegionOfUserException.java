package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedRegionOfUserException extends ServiceException {

    public DuplicatedRegionOfUserException() {
        super(HttpStatus.CONFLICT, "이미 지정한 지역입니다.");
    }
}
