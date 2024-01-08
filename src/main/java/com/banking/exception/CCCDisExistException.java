package com.banking.exception;

// class extends RuntimeException custome lại thông tin lỗi về CCCD của khách hàng không tồn tại
public class CCCDisExistException extends RuntimeException{
    public CCCDisExistException(String message) {
        super(message);
    }
}
