package com.banking.service;

import com.banking.dto.KhachHangDTO;
import com.banking.dto.KhachHangRequest;

import java.util.List;

public interface IKhachHangService {


    List<KhachHangDTO> findAllKhachHang();
    KhachHangDTO insertKhachHang(KhachHangRequest khachHangRequest);
    KhachHangDTO updateKhachHang(Long id, KhachHangRequest khachHangRequest);
    void deleteKhachHang(Long id);
    KhachHangDTO findKhachHangById(Long id);
}
