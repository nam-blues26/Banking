package com.banking.dto;

import com.banking.constant.MessageConstant;
import com.banking.constant.SwaggerConstant;
import com.banking.entity.GioiTinh;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.DeclareAnnotation;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangRequest {
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
}
