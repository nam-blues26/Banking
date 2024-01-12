package com.banking.controller;

import com.banking.entity.User;
import com.banking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * Xóa user theo
     * @param id của khách hàng kiểu integer
     * @return chuỗi -> thành công
     */
    @DeleteMapping
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    /**
     * Update user theo id
     * @param id -> id user
     * @param userName -> ten của usser
     * @param hoTen -> full name của user
     * @return user
     */
    @PutMapping
    public User updateUser(@PathVariable Integer id, @PathVariable String userName
    , @PathVariable String hoTen
    ) {
        return userService.updateUser(id, userName, hoTen);
    }
}
