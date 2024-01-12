package com.banking.repository;

import com.banking.dto.KhachHangDTO;
import com.banking.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
//    @Procedure(procedureName = "ThemKhachHang", outputParameterName = "out_check_insert")
    @Query(value = "CALL ThemKhachHang(:in_p_sdt,:in_p_cccd,:in_p_hoTen,:in_p_gioiTinh,:in_p_ngaySinh);", nativeQuery = true)
    Optional<KhachHang> themKhachHang(
            @Param("in_p_sdt") String sdt,
            @Param("in_p_cccd") String cccd,
            @Param("in_p_hoTen") String hoTen,
            @Param("in_p_gioiTinh") String gioiTinh,
            @Param("in_p_ngaySinh") LocalDate ngaySinh
    );

//    @Procedure(procedureName = "SelectAllKhachHang")
    @Query(value = "CALL SelectAllKhachHang()", nativeQuery = true)
    List<KhachHang> selectAllKhachHangBySP();


//    @Procedure(procedureName = "searchCustomerById")
    @Query(value = "CALL searchCustomerById(:inputId);", nativeQuery = true)
    KhachHang searchCustomerById(@Param("inputId") Long id);

    /**
     DELIMITER $
     create procedure updateKhachHang(
     in i_id bigint,
     in i_sdt varchar(10),
     in i_cccd varchar(12),
     in i_ho_ten nvarchar(20),
     in i_ngay_sinh date,
     in i_gioi_tinh varchar(10),
     out o_result int)
     begin
     if (select count(*) from khach_hang where id = i_id) = 0 then set o_result = 2 ;
     else
     if length(ltrim(i_sdt)) || length(ltrim(i_cccd))
     ||length(ltrim(i_ngay_sinh)) || length(ltrim(i_gioi_tinh)) || length(ltrim(i_ho_ten)) = 0 then
     set o_result = 1;
     else
     update khach_hang set
     ho_ten = i_ho_ten,
     cccd = i_cccd,
     gioi_tinh = i_gioi_tinh,
     ngay_sinh = i_ngay_sinh,
     sdt = i_sdt
     where id = i_id;
     set o_result = 0;
     end if;
     end if;
     end $
     DELIMITER ;
     */
    @Procedure(name = "updateKhachHang")
    int updateKhachHang(
            @Param("i_id") Long id,
            @Param("i_sdt") String sdt,
            @Param("i_cccd") String cccd,
            @Param("i_ho_ten") String hoTen,
            @Param("i_ngay_sinh") LocalDate ngaySinh,
            @Param("i_gioi_tinh") String gioiTinh
    );

    /**
     DELIMITER $
     create procedure deleteKhachhang(
     in i_id bigint,
     out result varchar(20))
     begin
     if (select count(*) from khach_hang where id = i_id) = 0 then set result = "khong tim thay user" ;
     else delete from khach_hang where id = i_id;
     set result = "xoa thanh cong";
     end if;
     end $
     DELIMITER ;
     */
    @Procedure(procedureName = "ThemKhachHang", outputParameterName = "out_check_delete")
    //    @Query(value = "CALL deleteKhachhang(:i_id);", nativeQuery = true)
    Boolean deleteKhachhang(@Param("i_id") Long id );

}
