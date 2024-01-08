package com.banking.service.Impl;

import com.banking.constant.Constant;
import com.banking.dto.KhachHangDTO;
import com.banking.exception.ExistException;
import com.banking.exception.NotFoundException;
import com.banking.model.KhachHang;
import com.banking.repository.IKhachHangRepository;
import com.banking.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Override
    public List<KhachHangDTO> findAllKhachHang() {
        return khachHangRepository.findAll().stream().map(KhachHangDTO::loadFromEntity).toList();
    }

    @Override
    public void insertKhachHang(KhachHangDTO khachHangDTO) {
        Optional<KhachHang> khachHang = khachHangRepository.findKhachHangByCccd(khachHangDTO.getCccd());
        if (khachHang.isPresent()){
            throw  new ExistException(Constant.MessageResponse.KH_CCCD_EXIST);
        }else {
            KhachHang newKhachHang = new KhachHang();
            newKhachHang.loadFromDTO(khachHangDTO);
            khachHangRepository.save(newKhachHang);
        }

    }
    @Override
    public void updateKhachHang(Long id, KhachHangDTO khachHangDTO) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(Constant.MessageResponse.KH_NOT_FOUND));

        khachHang.loadFromDTO(khachHangDTO);
        khachHangRepository.save(khachHang);
    }

    @Override
    public void deleteKhachHang(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(Constant.MessageResponse.KH_NOT_FOUND));

        khachHangRepository.delete(khachHang);
    }

    @Override
    public KhachHangDTO findKhachHangById(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(Constant.MessageResponse.KH_NOT_FOUND));
        return KhachHangDTO.loadFromEntity(khachHang);
    }
}
