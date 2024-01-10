package com.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class tiếp nhận req body
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthencationDTO {

    private String username;//tên đăng nhập người dùng

    private String password;//mk người dùng
}
