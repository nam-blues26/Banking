package com.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*
* Kiểu trả về của token
* */
@Builder
@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;// token trả về
}
