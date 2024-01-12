package com.banking.controller;

import com.banking.dto.KhachHangDTO;
import com.banking.dto.KhachHangRequest;
import com.banking.entity.KhachHang;
import com.banking.service.Impl.KhachHangServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Class định nghĩa các api crud, search bảng khachhang
// Endpoint: /api/khach-hang/
@RestController
@Tag(name = "Khach hang", description = "KhachHang APIs")
@RequestMapping("${project.bank.version.v1}/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangServiceImpl khachHangService;
    /**
     * @return trả về danh sách khách hàng, trạng thái thành công
     */
    @Operation(summary = "API Xem danh sách khách hàng", description = "trả về danh sách khách hàng")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả danh sách khách hàng"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập")
    })
    @GetMapping()
    public ResponseEntity<List<KhachHang>> getAll() {
        List<KhachHang> khachHangList= khachHangService.findAllKhachHang();
        return new ResponseEntity<>(khachHangList, HttpStatus.OK);
    }

    /**
     * API tìm khách hàng theo id
     * Tham số đường dẫn khachHangId với kiểu dữ liệu long
     * @param id map với tham số đường dẫn khachHangId
     * @return khachhangDTO với trạng thái thành công
     */
    @Operation(summary = "API Xem thông tin khách hàng", description = "trả về khách hàng")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về thông tin khách hàng"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy khách hàng")
    })
    @Parameter(name = "id", description = "nhập id khách hàng")
    @GetMapping("/{id}")
    public ResponseEntity<KhachHang> getKhachHangById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(khachHangService.findKhachHangById(id), HttpStatus.OK);
    }

    /**
     * API thêm khách hàng
     * @param khachHangRequest với các thuộc tính:
     * ID, SDT, CCCD, Hoten, GioiTinh, NgaySinh
     * @return khachHanhDTO với trạng thái thành công
     */
    @Operation(summary = "API Thêm khách hàng", description = "trả về khách hàng vừa thêm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về thông tin khách hàng"),
            @ApiResponse(responseCode = "400", description = "Nhập không đúng thông tin khách hàng"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "409", description = "Trùng căn cước công dân")
    })
    @PostMapping
    public ResponseEntity<KhachHang> createKhachHang(
            @RequestBody @Valid KhachHangRequest khachHangRequest
    ) {
        return new ResponseEntity<>(khachHangService.insertKhachHang(khachHangRequest), HttpStatus.OK);
    }

    /**
     * API sửa khách hàng theo id
     * @param id map với tham số đường dẫn id với kiểu dữ liệu long
     * @param khachHangRequest với các thuộc tính
     * SDT, CCCD, Hoten, GioiTinh, NgaySinh
     * @return Trả về true với trạng thái thành công
     */
    @Operation(summary = "API Sửa khách hàng", description = "trả về khách hàng vừa sửa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về thông tin khách hàng"),
            @ApiResponse(responseCode = "400", description = "Nhập không đúng thông tin khách hàng"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy khách hàng")
    })
    @Parameter(name = "id", description = "nhập id khách hàng")
    @PutMapping("/{id}")
    public ResponseEntity<KhachHang> updateKhachHang(
            @PathVariable Long id,
            @RequestBody @Valid KhachHangRequest khachHangRequest){

        return new ResponseEntity<>(khachHangService.updateKhachHang(id,khachHangRequest), HttpStatus.OK);
    }

    /**
     * API xóa khách hàng theo id
     * @param id map với tham số đường dẫn id với kiểu dữ liệu long
     * @return Trả về true với trạng thái thành công
     */
    @Operation(summary = "API Xóa khách hàng")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công trả về true"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy khách hàng")
    })
    @Parameter(name = "id", description = "nhập id khách hàng")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteKhachHang(
            @PathVariable Long id
    ){
        khachHangService.deleteKhachHang(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
