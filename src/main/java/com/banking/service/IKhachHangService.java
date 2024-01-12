package com.banking.service;

import com.banking.dto.KhachHangDTO;
import com.banking.dto.KhachHangRequest;
import com.banking.entity.KhachHang;

import java.util.List;

public interface IKhachHangService {


    List<KhachHang> findAllKhachHang();
    KhachHang insertKhachHang(KhachHangRequest khachHangRequest);
    KhachHang updateKhachHang(Long id, KhachHangRequest khachHangRequest);
    void deleteKhachHang(Long id);
    KhachHang findKhachHangById(Long id);
}
