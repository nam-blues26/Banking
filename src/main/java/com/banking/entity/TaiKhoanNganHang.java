package com.banking.entity;

import com.banking.constant.Constant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiKhoanNganHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d+",message = Constant.MessageResponse.TKNH_SOTK_REGEX)
    private String soTK;

    private Long soDu;

    @Enumerated(EnumType.ORDINAL)
    private LoaiTK loaiTK;

    @ManyToOne
    @JoinColumn(name = "khacHangID", nullable = false)
    private KhachHang khachHangTK;
}
