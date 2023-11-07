package com.capstonedesign.inhalife.department.exception;

import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.HttpStatus;


public class NotExistedDepartmentException extends ServiceException {

    public NotExistedDepartmentException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 학과입니다.");
    }
}
