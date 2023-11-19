package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedFriendException extends ServiceException {

    public NotExistedFriendException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 친구입니다.");
    }
}
