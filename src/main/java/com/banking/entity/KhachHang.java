package com.banking.entity;

import com.banking.constant.MessageConstant;
import com.banking.dto.KhachHangDTO;
import com.banking.dto.KhachHangRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
// class map với bảng khachhang trong db
// Thuộc tính :
/*id: primary key kiểu long, giá trị tự động tăng
 * sdt: ràng buộc not blank, maxlength = 11, chỉ chứa số
 * cccd: ràng buộc not blank, maxlength = 12, chi chứa số
 * hoTen: ràng buộc not blank
 * ngaySinh: ràng buộc not null, không được là ngày trong tương lai 9/1/2024 -> error
 */
// Hàm loadFromDTO chuyển đỗi dữ liệu từ khachhangDTO sang khachhang
// Tham số nhận vào là 1 khachhangDTO
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = MessageConstant.KH_SDT_NOT_BLANK)
    @Size(max = 11, message = MessageConstant.KH_SDT_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = MessageConstant.KH_SDT_REGEX)
    private String sdt;

    @NotBlank(message = MessageConstant.KH_CCCD_NOT_BLANK)
    @Size(max = 12, message = MessageConstant.KH_CCCD_MAX_LENGHT)
    @Pattern(regexp = "\\d+", message = MessageConstant.KH_CCCD_REGEX)
    @Column(unique = true)
    private String cccd;

    @NotBlank(message = MessageConstant.KH_HOTEN_NOT_BLANK)
    private String hoTen;

    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    @NotNull(message = MessageConstant.KH_NGAYSINH_NOT_NULL)
    @Past(message = MessageConstant.KH_NGAYSINH_PAST)
    private LocalDate ngaySinh;


    public void loadFromDTO(KhachHangRequest khachHangDTO) {
        this.sdt = khachHangDTO.getSdt();
        this.cccd = khachHangDTO.getCccd();
        this.hoTen = khachHangDTO.getHoTen();
        this.gioiTinh = khachHangDTO.getGioiTinh();
        this.ngaySinh = khachHangDTO.getNgaySinh();
    }
}
