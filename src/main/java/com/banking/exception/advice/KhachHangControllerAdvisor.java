package com.banking.exception.advice;

import com.banking.exception.ExistException;
import com.banking.exception.ErrorResponse;
import com.banking.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class KhachHangControllerAdvisor {

    @ExceptionHandler(ExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //409
    public ErrorResponse CCCDisExistHandler(ExistException ex, WebRequest request){
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                new Date(),
                List.of(ex.getMessage()),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse KhachHangNotFoundHandler(NotFoundException ex, WebRequest request){
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                List.of(ex.getMessage()),
                request.getDescription(false)
        );
    }
}
