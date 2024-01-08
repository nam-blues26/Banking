package com.banking.repository;

import com.banking.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// class extends JpaRepository kế thừa các phương thức tiện ích của hibernate
@Repository
public interface IKhachHangRepository extends JpaRepository<KhachHang,Long > {
    // Hàm tim khách hàng theo cccd với tham só truyền vào là 1 chuỗi cccd
    // trả ra 1 optional -> tránh exception null pointer khi không tìm thấy khách hàng
    Optional<KhachHang> findKhachHangByCccd(String cccd);
    // Hàm tim khách hàng theo id khách hàng với tham só truyền vào là 1 chuỗi cccd
    // trả ra 1 optional -> tránh exception null pointer khi không tìm thấy khách hàng  
      Optional<KhachHang> findKhachHangById(Long id);
}
