package com.banking.dto;

import com.banking.constant.Constant;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDTO {
    @NotBlank(message = Constant.MessageResponse.KH_SDT_NOT_BLANK)
    @Size(max = 11, message = Constant.MessageResponse.KH_SDT_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = Constant.MessageResponse.KH_SDT_REGEX)
    @Schema(example = Constant.SwaggerExValue.KH_SDT)
    private String sdt;

    @NotBlank(message = Constant.MessageResponse.KH_CCCD_NOT_BLANK)
    @Size(max = 12, message = Constant.MessageResponse.KH_CCCD_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = Constant.MessageResponse.KH_CCCD_REGEX)
    @Column(unique = true)
    @Schema(example = Constant.SwaggerExValue.KH_CCCD)
    private String cccd;

    @NotBlank(message = Constant.MessageResponse.KH_HOTEN_NOT_BLANK)
    @Schema(example = Constant.SwaggerExValue.KH_HO_TEN)
    private String hoTen;

    @Enumerated(EnumType.STRING)
    @Schema(example = Constant.SwaggerExValue.KH_GIOI_TINH)
    private GioiTinh gioiTinh;

    @NotNull(message = Constant.MessageResponse.KH_NGAYSINH_NOT_NULL)
    @Past(message = Constant.MessageResponse.KH_NGAYSINH_PAST)
    @Schema(example = Constant.SwaggerExValue.KH_NGAY_SINH)
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
