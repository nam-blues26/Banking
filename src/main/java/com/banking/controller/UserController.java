package com.banking.controller;

import com.banking.constant.MessageConstant;
import com.banking.dto.UserDTO;
import com.banking.entity.User;
import com.banking.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User", description = "User APIs")
@RequestMapping("${project.bank.version.v1}/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @Operation(summary = "API Thêm user", description = "trả về trạng thái thêm thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về User"),
            @ApiResponse(responseCode = "409", description = "tìm thấy người dùng đã tồn tại")
    })
    /**
     * Thêm user
     *
     * @param
     * @return Thông tin user vừa thêm
     */
    @PostMapping("{id}")
    public ResponseEntity addUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.addUser(userDTO));
    }

    @Operation(summary = "API Xóa user theo id", description = "trả về trạng thái xóa thành công")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công xóa user"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy user")
    })
    @Parameter(name = "id", description = "nhập id user")
    /**
     * Xóa user theo
     *
     * @param id -> id của khách hàng kiểu integer
     * @return chuỗi -> thành công status 200
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body(MessageConstant.DELETE_SUSSCES);
    }

    @Operation(summary = "API update user theo id", description = "trả về trạng thái xóa thành công và user đã udpate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công update user"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy user")
    })
    @Parameter(name = "id", description = "nhập id user")
    @Parameter(name = "UserDto", description = "nhập thông tin user cần update")
    /**
     * Update user theo id của user
     * @param id -> id user
     * @param userDTO thông tin: username, fullname, password, ngaysinh
     * @return user với trạng thái update thành công
     */

    @PutMapping("{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.updateUser(id, userDTO));
    }

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
