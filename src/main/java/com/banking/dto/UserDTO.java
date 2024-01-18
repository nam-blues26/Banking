package com.banking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO của người dùng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "{user.fullName.notBlack}")
    private String fullName;
    @NotBlank(message = "{user.username.notBlack}")
    private String username;
    @NotBlank(message = "{user.password.notBlack}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{4,}$", message = "user.password.regix")
    private String password;

    @NotNull(message = "{user.dob.notNull}")
    @Past(message = "{user.dob.invalid}")
    private LocalDate ngaySinh;


}
