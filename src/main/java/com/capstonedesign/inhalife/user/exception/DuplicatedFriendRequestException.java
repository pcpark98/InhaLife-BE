package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicatedFriendRequestException extends ServiceException {

    public DuplicatedFriendRequestException() {
        super(HttpStatus.CONFLICT, "이미 친구 신청을 보낸 사용자입니다.");
    }
}
