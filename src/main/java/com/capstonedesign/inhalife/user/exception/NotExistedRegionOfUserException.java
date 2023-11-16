package com.capstonedesign.inhalife.user.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedRegionOfUserException extends ServiceException {

    public NotExistedRegionOfUserException() {
        super(HttpStatus.NOT_FOUND, "지정되지 않은 지역입니다.");
    }
}
