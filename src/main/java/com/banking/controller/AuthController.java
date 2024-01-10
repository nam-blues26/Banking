package com.banking.controller;

import com.banking.constant.UserConstant;
import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.exception.NotFoundException;
import com.banking.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping("/auth")
public class AuthController {
    //api dangky, dangnhap

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
    @PostMapping("/login-createToken")
    public ResponseEntity<TokenDTO> createToken(@RequestBody AuthencationDTO authencationDTO) {
        //Xác thực từ username và password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authencationDTO.getUsername(),
                        authencationDTO.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            // Set thông tin authentication vào Security Context
//            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Trả về jwt cho người dùng.
            String token = jwtService.generateToken(authencationDTO.getUsername());
            TokenDTO tokenDTO = TokenDTO.builder().token(token).build();
            return ResponseEntity.ok(tokenDTO);
        } else {
            throw new NotFoundException(UserConstant.LOGIN_FAILL);
        }
    }
}
