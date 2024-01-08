package com.banking.service.Impl;

import com.banking.constant.Constant;
import com.banking.dto.KhachHangDTO;
import com.banking.exception.ExistException;
import com.banking.exception.NotFoundException;
import com.banking.entity.KhachHang;
import com.banking.repository.IKhachHangRepository;
import com.banking.service.IKhachHangService;
import com.banking.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Class xử lí logic liên quan đến bảng khách hàng
// Thực thi lại các phưởng thức interface IKhachHangService
@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khachHangRepository;

    // Gọi hàm loadFromEntity trong object khachhangDTO chuyển khachhang -> khachhangDTO
    // trả về 1 danh sách khachhangDTO
    @Override
    public List<KhachHangDTO> findAllKhachHang() {
        return khachHangRepository.findAll().stream().map(KhachHangDTO::loadFromEntity).toList();
    }

    // Thêm 1 khách hàng với tham số truyền vào là 1 object khachhangDTO
    // case 1 : trùng cccd sẽ trả về class lỗi CCCDisExistException
    // case 2 : cccd mới -> thêm vào db
    @Override
    public void insertKhachHang(KhachHangDTO khachHangDTO) {
        Optional<KhachHang> khachHang = khachHangRepository.findKhachHangByCccd(khachHangDTO.getCccd());
        if (khachHang.isPresent()){
            throw  new ExistException(Constant.MessageResponse.KH_CCCD_EXIST);
        }else {
            KhachHang newKhachHang = new KhachHang();
//            newKhachHang.loadFromDTO(khachHangDTO);
            MapperUtils.entityToDTO(newKhachHang, KhachHangDTO.class);
            khachHangRepository.save(newKhachHang);
            //return dữ liệu
        }

    }

    // Sửa 1 khách hàng với tham số truyền vào là 1 object khachhangDTO và id khách hàng với kiểu dữ liệu long
    // case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
    // case 2 : tìm thấy khách hàng với id truyền vào -> update vào db
    @Override
    public void updateKhachHang(Long id, KhachHangDTO khachHangDTO) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(Constant.MessageResponse.KH_NOT_FOUND));

//        khachHang.loadFromDTO(khachHangDTO);
        MapperUtils.entityToDTO(khachHang, KhachHangDTO.class);
        khachHangRepository.save(khachHang);
    }

    // case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
    // case 2 : tìm thấy khách hàng với id truyền vào -> delete vào db
    @Override
    public void deleteKhachHang(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(Constant.MessageResponse.KH_NOT_FOUND));

        khachHangRepository.delete(khachHang);
    }

    // Tìm khách hàng theo id khách hàng truyền vào
    // case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
    // case 2 : tìm thấy khách hàng với id truyền vào -> delete vào db
    @Override
    public KhachHangDTO findKhachHangById(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(Constant.MessageResponse.KH_NOT_FOUND));
        return KhachHangDTO.loadFromEntity(khachHang);
    }
}
