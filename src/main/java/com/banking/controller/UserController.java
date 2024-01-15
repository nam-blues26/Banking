package com.banking.controller;

import com.banking.entity.KhachHang;
import com.banking.entity.User;
import com.banking.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private IUserService userService;

    @Operation(summary = "API Xem danh sách user", description = "trả về danh sách user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả user"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @GetMapping("/get-all-user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> listUser = userService.viewAllUser();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @Operation(summary = "API Xem danh sách user theo id", description = "trả về danh sách user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả user"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "404", description = "User không tồn tại")
    })
    @GetMapping("detail-user/{id}")
    public ResponseEntity<User> detailUser(@PathVariable("id") Integer id){
        User userDetail = userService.userDetail(id);
        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

}
