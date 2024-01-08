package com.banking.dto;

import com.banking.model.GioiTinh;
import com.banking.model.KhachHang;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// class chuyển đỗi dữ liệu từ entity khachhang
// Với các thuộc tính : sdt, cccd, hoten, gioitinh, ngaysinh
// Hàm loadFromEntity chuyển data từ entity khachhang sang khachhangDTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO {
    @NotBlank(message = "{khachhang.sdt.notblank}")
    @Size(max = 11, message = "{khachhang.sdt.maxlength}")
    @Pattern(regexp = "\\d+", message = "{khachhang.sdt.regex}")
    @Schema(example = "09127362")
    private String sdt;

    @NotBlank(message = "{khachhang.cccd.notblank}")
    @Size(max = 12, message = "{khachhang.cccd.maxlength}")
    @Pattern(regexp = "\\d+", message = "{khachhang.cccd.regex}")
    @Column(unique = true)
    @Schema(example = "0123456789")
    private String cccd;

    @NotBlank(message = "{khachhang.hoten.notblank}")
    @Schema(example = "Vũ Nam")
    private String hoTen;

    @Enumerated(EnumType.STRING)
    @Schema(example = "Nam")
    private GioiTinh gioiTinh;

    @NotNull(message = "{khachhang.ngaysinh.notnull}")
    @Past(message = "{khachhang.ngaysinh.past}")
    @Schema(example = "1992-06-26")
    private LocalDate ngaySinh;

    public static KhachHangDTO loadFromEntity(KhachHang khacHang) {
        KhachHangDTO khachHangDTO = new KhachHangDTO();
        khachHangDTO.setSdt(khacHang.getSdt());
        khachHangDTO.setCccd(khacHang.getCccd());
        khachHangDTO.setHoTen(khacHang.getHoTen());
        khachHangDTO.setGioiTinh(khacHang.getGioiTinh());
        khachHangDTO.setNgaySinh(khacHang.getNgaySinh());
        return khachHangDTO;
    }
}
