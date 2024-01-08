package com.banking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// class map với bảng taikhoannganhang trong db
/*Thuộc tính: id kiểu long primary key với giá trị tự tăng
 * soTK: ràng buộc chỉ chấp nhận sô
 * sodu
 * loaiTk : nhận kiểu dữ liệu enum với 1 trong các giá trị TietKiem, ThanhToan
 * khachhangTk: tạo mối quan hệ với bảng khachhang (n-1) thông qua khóa ngoại khachhangID
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoanNganHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d+",message = "Số tài khoản chỉ nhập số")
    @Column(name = "soTaiKhoan")
    private String soTK;

    private Long soDu;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "loaiTaiKhoan")
    private LoaiTK loaiTK;

    @ManyToOne
    @JoinColumn(name = "khacHangID", nullable = false)
    private KhachHang khachHangTK;
}
