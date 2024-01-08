package com.banking.exception;

// class extends RuntimeException custome lại thông tin đã tồn tại
public class ExistException extends RuntimeException{
    public ExistException(String message) {
        super(message);
    }
}
