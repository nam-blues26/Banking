package com.banking.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String username;//tên đăng nhập người dùng

    @NotBlank
    private String password;//mk người dùng
}
