package com.capstonedesign.inhalife.subject.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;

public class NotExistedProfessorException extends ServiceException {

    public NotExistedProfessorException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 교수입니다.");
    }
}
