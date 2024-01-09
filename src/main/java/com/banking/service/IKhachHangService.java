package com.banking.service;

import com.banking.dto.KhachHangDTO;

import java.util.List;

public interface IKhachHangService {


    List<KhachHangDTO> findAllKhachHang();
    void insertKhachHang(KhachHangDTO khachHangDTO);
    void updateKhachHang(Long id, KhachHangDTO khachHangDTO);
    void deleteKhachHang(Long id);
    KhachHangDTO findKhachHangById(Long id);
}
