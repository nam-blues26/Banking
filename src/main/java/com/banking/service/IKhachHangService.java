package com.banking.service;

import com.banking.dto.KhachHangDTO;
import com.banking.model.KhachHang;

import java.util.List;

// Interface định nghĩa các phương thức dùng chung cho các class trong service
// CRUD, search
public interface IKhachHangService {

    List<KhachHangDTO> findAllKhachHang();
    void insertKhachHang(KhachHangDTO khachHangDTO);
    void updateKhachHang(Long id, KhachHangDTO khachHangDTO);
    void deleteKhachHang(Long id);
    KhachHangDTO findKhachHangById(Long id);
}
