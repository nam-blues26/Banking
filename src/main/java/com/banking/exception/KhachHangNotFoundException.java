package com.banking.exception;

// class extends RuntimeException custome lại thông tin lỗi về khách hàng không tồn tại
public class KhachHangNotFoundException extends RuntimeException {
    public KhachHangNotFoundException(String message) {
        super(message);
    }
}

