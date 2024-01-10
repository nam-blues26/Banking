package com.banking.controller;

import com.banking.constant.MessageConstant;
import com.banking.constant.UserConstant;
import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import com.banking.exception.NotFoundException;
import com.banking.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banking.entity.User;
import com.banking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class tạo api đăng ký, api đăng nhập -> token
 * endpoint /auth/**
 */
@RestController
@RequestMapping("${project.bank.version.v1}/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "API login xác thực người dùng", description = "Nếu xác thực thành công trả về 1 token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về token"),
            @ApiResponse(responseCode = "403", description = "Không tìm thấy người dùng (username và password không hợp lệ)")
    })
    @Parameters(@Parameter(name = "AuthencationDTO.class", description = "Gửi 2 trường username và password"))
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> createToken(@RequestBody @Valid AuthencationDTO authencationDTO) {
        return ResponseEntity.ok().body(userService.login(authencationDTO));
    }

    /**
     * API thêm user
     * @param userDTO -> thuộc tính id, fullName, UserName, passWord
     * @return user với trạng thái thành công
     */
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.addUser(userDTO));
    }
}
