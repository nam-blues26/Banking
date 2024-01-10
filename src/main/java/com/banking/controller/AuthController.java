package com.banking.controller;

import com.banking.entity.User;
import com.banking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class tạo api đăng ký
 * endpoint api/v1/register
 */
@RestController
@RequestMapping("api/v1/register")
public class AuthController {
    @Autowired
    private IUserService userService;
    //api dangky, dangnhap

    /**
     * API thêm user
     * @param user -> thuộc tính id, fullName, UserName, passWord
     * @return user với trạng thái thành công
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.addUser(user));
    }
}
