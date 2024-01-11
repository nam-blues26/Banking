package com.banking.dto;

import com.banking.constant.MessageConstant;
import com.banking.constant.SwaggerConstant;
import com.banking.entity.GioiTinh;
import com.banking.entity.KhachHang;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = MessageConstant.KH_SDT_NOT_BLANK)
    @Size(max = 11, message = MessageConstant.KH_SDT_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = MessageConstant.KH_SDT_REGEX)
    @Schema(example = SwaggerConstant.KH_SDT)
    private String sdt;

    @NotBlank(message = MessageConstant.KH_CCCD_NOT_BLANK)
    @Size(max = 12, message = MessageConstant.KH_CCCD_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = MessageConstant.KH_CCCD_REGEX)
    @Column(unique = true)
    @Schema(example = SwaggerConstant.KH_CCCD)
    private String cccd;

    @NotBlank(message = MessageConstant.KH_HOTEN_NOT_BLANK)
    @Schema(example = SwaggerConstant.KH_HO_TEN)
    private String hoTen;

    @Enumerated(EnumType.STRING)
    @Schema(example = SwaggerConstant.KH_GIOI_TINH)
    private GioiTinh gioiTinh;

    @NotNull(message = MessageConstant.KH_NGAYSINH_NOT_NULL)
    @Past(message = MessageConstant.KH_NGAYSINH_PAST)
    @Schema(example = SwaggerConstant.KH_NGAY_SINH)
    private LocalDate ngaySinh;

    public static KhachHangDTO loadFromEntity(KhachHang khacHang) {
        KhachHangDTO khachHangDTO = new KhachHangDTO();
        khachHangDTO.setId(khacHang.getId());
        khachHangDTO.setSdt(khacHang.getSdt());
        khachHangDTO.setCccd(khacHang.getCccd());
        khachHangDTO.setHoTen(khacHang.getHoTen());
        khachHangDTO.setGioiTinh(khacHang.getGioiTinh());
        khachHangDTO.setNgaySinh(khacHang.getNgaySinh());
        return khachHangDTO;
    }
}
