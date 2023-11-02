package com.capstonedesign.inhalife.exception;

import com.capstonedesign.inhalife.exception.domain.Response;
import com.capstonedesign.inhalife.exception.domain.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<?> handleServiceException(ServiceException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(Response.error(e.getMessage()));
    }
}
