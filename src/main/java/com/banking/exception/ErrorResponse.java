package com.banking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

// class lưu thông tin lỗi trả về cho client
// Thuộc tính statuscode: mã lỗi
// timestamp: ngày hiện lỗi
// message: các lỗi tìm được
// description: api lỗi
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private Date timestamp;
    private List<String> message;
    private String description;
}

