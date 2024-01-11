package com.banking.controller;

import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.entity.User;
import com.banking.service.IUserService;

/**
 * Class tạo api đăng ký, api đăng nhập -> token
 * endpoint /auth/**
 */
@RestController
@RequestMapping("${project.bank.version.v1}/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    /**
     *
     * @param authencationDTO : username, password
     * @return User có thông tin: Id, full name, username, password
     */

    @Operation(summary = "API login xác thực người dùng", description = "Nếu đăng ký thành công trả về thông tin tài khoản người dùng")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về thông tin tài khoản người dùng"),
            @ApiResponse(responseCode = "403", description = "Không đúng mật khẩu"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy username")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> createToken(@RequestBody @Valid AuthencationDTO authencationDTO) {
        return ResponseEntity.ok().body(userService.login(authencationDTO));
    }

    /**
     * API thêm user
     * @param userDTO -> thuộc tính  fullName, UserName, passWord
     * @return user với trạng thái thành công
     */
    @Operation(summary = "API đăng ký", description = "Nếu xác thực thành công trả về token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về User"),
            @ApiResponse(responseCode = "409", description = "tìm thấy người dùng đã tồn tại")
    })
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.addUser(userDTO));
    }
}
