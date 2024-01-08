package com.banking.controller;

import com.banking.dto.KhachHangDTO;
import com.banking.service.Impl.KhachHangServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// class khai báo các api phụ trách crud, search bảng khachhang
@RestController
@Tag(name = "Khach hang", description = "KhachHang APIs")
@RequestMapping("/api/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangServiceImpl khachHangService;

    // API trả về toàn bộ khách hàng, trạng thái thành công
    @Operation(description = "Xem danh sách Khách hàng")
    @GetMapping()
    public ResponseEntity<List<KhachHangDTO>> getAll() {

        List<KhachHangDTO> khachHangList= khachHangService.findAllKhachHang();
        return new ResponseEntity<>(khachHangList, HttpStatus.OK);
    }

    // API tìm khách hàng theo id
    // Tham số truyền vào với kiểu dữ liệu là long. Tên param: kiểu dữ liệu: m
    // Trả về thông tin khách hàng với trạng thái thành công
    @Operation(description = "Xem thông tin khách hàng")

    @GetMapping("/{khachHangId}")
    public ResponseEntity<KhachHangDTO> getKhachHangById(
            @PathVariable(name = "khachHangId") Long id
    ) {

        KhachHangDTO khachHang = khachHangService.findKhachHangById(id);
        return new ResponseEntity<>(khachHang, HttpStatus.OK);
    }

    // API thêm khách hàng
    // Tham số vào là 1 object với các thuộc tính:
    // SDT, CCCD, Hoten, GioiTinh, NgaySinh
    // Trả về thông tin khách hàng với trạng thái thành công
    @Operation(description = "Tạo Khách hàng")
    @PostMapping
    public ResponseEntity<KhachHangDTO> createKhachHang(
            @RequestBody @Valid KhachHangDTO khachHangDTO
    ) {

        khachHangService.insertKhachHang(khachHangDTO);
        return new ResponseEntity<>(khachHangDTO, HttpStatus.OK);
    }

    // API sửa khách hàng theo id
    // Tham số truyền vào thứ 1 là id của khách hàng với kiểu dữ liệu là long
    // Tham số truyền vào thứ 2 là 1 object với các thuộc tính :
    // SDT, CCCD, Hoten, GioiTinh, NgaySinh
    // Trả về true với trạng thái thành công
    @Operation(description = "Sửa Khách hàng")

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateKhachHang(
            @PathVariable Long id,
            @RequestBody @Valid KhachHangDTO khachHangDTO){
        khachHangService.updateKhachHang(id,khachHangDTO);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    // API xóa khách hàng theo id
    // Tham số truyền vào là id của khách hàng với kiểu dữ liệu là long
    // Trả về true với trạng thái thành công
    @Operation(description = "Xóa Khách hàng")

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteKhachHang(
            @PathVariable Long id
    ){
        khachHangService.deleteKhachHang(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
