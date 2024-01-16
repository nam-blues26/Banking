package com.banking.controller;

import com.banking.entity.KhachHang;
import com.banking.entity.Role;
import com.banking.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Role", description = "Role APIs")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Operation(summary = "API Xem danh sách role", description = "trả về danh sách role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả danh sách role"),
//            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @GetMapping()
    public ResponseEntity<List<Role>> getAll() {
        return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
    }

}
