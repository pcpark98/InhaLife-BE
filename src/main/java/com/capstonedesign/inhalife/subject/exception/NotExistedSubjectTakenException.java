package com.capstonedesign.inhalife.subject.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedSubjectTakenException extends ServiceException {

    public NotExistedSubjectTakenException() {
        super(HttpStatus.NOT_FOUND, "수강하지 않은 과목입니다.");
    }
}
