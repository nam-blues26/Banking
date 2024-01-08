package com.banking.exception;

// class extends RuntimeException custome lại thông tin lỗi về khách hàng không tồn tại
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

