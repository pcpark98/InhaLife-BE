package com.capstonedesign.inhalife.subject.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedSubjectException extends ServiceException {

    public NotExistedSubjectException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 과목입니다.");
    }
}
