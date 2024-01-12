package com.banking.repository;

import com.banking.dto.KhachHangDTO;
import com.banking.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// class extends JpaRepository kế thừa các phương thức tiện ích của hibernate
@Repository
public interface IKhachHangRepository extends JpaRepository<KhachHang,Long > {
    /**
     *
     * @param cccd truyền vào căn cước công dân
     * @return Optional<KhachHang> -> tránh exception null pointer khi không tìm thấy khách hàng
     */
    Optional<KhachHang> findKhachHangByCccd(String cccd);

    /**
     *
     * @param id truyền vào id của khách hàng
     * @return Optional<KhachHang> -> tránh exception null pointer khi không tìm thấy khách hàng
     */
    Optional<KhachHang> findKhachHangById(Long id);


    /**
     * gọi Store Procedure ThemKhacHang
     * @param sdt
     * @param cccd
     * @param hoTen
     * @param gioiTinh
     * @param ngaySinh
     */
    @Procedure(procedureName = "ThemKhachHang", outputParameterName = "out_check_insert")
    Boolean themKhachHang(
            @Param("in_p_sdt") String sdt,
            @Param("in_p_cccd") String cccd,
            @Param("in_p_hoTen") String hoTen,
            @Param("in_p_gioiTinh") String gioiTinh,
            @Param("in_p_ngaySinh") LocalDate ngaySinh
    );

    @Procedure(procedureName = "SelectAllKhachHang")
    List<KhachHangDTO> selectAllKhachHang();

    @Procedure(procedureName = "searchCustomerById")
    KhachHang searchCustomerById(@Param("id") Long id);
}
