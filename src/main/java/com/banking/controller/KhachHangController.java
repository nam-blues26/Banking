package com.banking.controller;

import com.banking.dto.KhachHangDTO;
import com.banking.model.KhachHang;
import com.banking.service.Impl.KhachHangServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Class định nghĩa các api crud, search bảng khachhang
// Endpoint: /api/khach-hang/
@RestController
@Tag(name = "Khach hang", description = "KhachHang APIs")
@RequestMapping("/api/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangServiceImpl khachHangService;
    /**
     * @return trả về danh sách khách hàng, trạng thái thành công
     */
    @Operation(description = "Xem danh sách Khách hàng")
    @GetMapping()
    public ResponseEntity<List<KhachHangDTO>> getAll() {
        List<KhachHangDTO> khachHangList= khachHangService.findAllKhachHang();
        return new ResponseEntity<>(khachHangList, HttpStatus.OK);
    }

    /**
     * API tìm khách hàng theo id
     * Tham số đường dẫn khachHangId với kiểu dữ liệu long
     * @param id map với tham số đường dẫn khachHangId
     * @return khachhangDTO với trạng thái thành công
     */
    @Operation(description = "Xem thông tin khách hàng")
    @GetMapping("/{khachHangId}")
    public ResponseEntity<KhachHangDTO> getKhachHangById(
            @PathVariable(name = "khachHangId") Long id
    ) {
        KhachHangDTO khachHang = khachHangService.findKhachHangById(id);
        return new ResponseEntity<>(khachHang, HttpStatus.OK);
    }

    /**
     * API thêm khách hàng
     * @param khachHangDTO với các thuộc tính:
     * ID, SDT, CCCD, Hoten, GioiTinh, NgaySinh
     * @return khachHanhDTO với trạng thái thành công
     */
    @Operation(description = "Tạo Khách hàng")
    @PostMapping
    public ResponseEntity<KhachHangDTO> createKhachHang(
            @RequestBody @Valid KhachHangDTO khachHangDTO
    ) {
        khachHangService.insertKhachHang(khachHangDTO);
        return new ResponseEntity<>(khachHangDTO, HttpStatus.OK);
    }

    /**
     * API sửa khách hàng theo id
     * @param id map với tham số đường dẫn id với kiểu dữ liệu long
     * @param khachHangDTO với các thuộc tính
     * SDT, CCCD, Hoten, GioiTinh, NgaySinh
     * @return Trả về true với trạng thái thành công
     */
    @Operation(description = "Sửa Khách hàng")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateKhachHang(
            @PathVariable Long id,
            @RequestBody @Valid KhachHangDTO khachHangDTO){
        khachHangService.updateKhachHang(id,khachHangDTO);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * API xóa khách hàng theo id
     * @param id map với tham số đường dẫn id với kiểu dữ liệu long
     * @return Trả về true với trạng thái thành công
     */
    @Operation(description = "Xóa Khách hàng")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteKhachHang(
            @PathVariable Long id
    ){
        khachHangService.deleteKhachHang(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
