package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedFriendException extends ServiceException {

    public DuplicatedFriendException() {
        super(HttpStatus.CONFLICT, "이미 친구인 사용자입니다.");
    }
}
