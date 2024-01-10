package com.banking.service.Impl;

import com.banking.constant.MessageConstant;
import com.banking.dto.KhachHangDTO;
import com.banking.dto.KhachHangRequest;
import com.banking.exception.ExistException;
import com.banking.exception.NotFoundException;
import com.banking.entity.KhachHang;
import com.banking.repository.IKhachHangRepository;
import com.banking.service.IKhachHangService;
import com.banking.service.base.IMessageService;
import com.banking.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Class xử lí logic liên quan đến bảng khách hàng
// Thực thi lại các phương thức interface IKhachHangService
@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Autowired
    private IMessageService messageService;
    /**
     * @return 1 danh sách khachhangDTO
     */
    @Override
    public List<KhachHangDTO> findAllKhachHang() {
        return khachHangRepository.findAll().stream().map(KhachHangDTO::loadFromEntity).toList();
    }

    /**
     * @param khachHangRequest với các thuộc tính:
     * SDT, CCCD, Hoten, GioiTinh, NgaySinh
     * case 1 : trùng cccd sẽ trả về class lỗi CCCDisExistException
     * case 2 : cccd mới -> thêm vào db
     */
    @Override
    public KhachHangDTO insertKhachHang(KhachHangRequest khachHangRequest) {
        Optional<KhachHang> khachHang = khachHangRepository.findKhachHangByCccd(khachHangRequest.getCccd());
        if (khachHang.isPresent()){
            throw  new ExistException(messageService.getMessage(MessageConstant.KH_CCCD_EXIST));
        }else {
            KhachHang newKhachHang =khachHangRepository.save(MapperUtils.dtoToEntity(khachHangRequest, KhachHang.class));

            return MapperUtils.entityToDTO(newKhachHang, KhachHangDTO.class);
        }
    }
    /**
     * @param id -> id của khách hàng kiểu dữ liệu long
     * @param khachHangRequest -> SDT, CCCD, Hoten, GioiTinh, NgaySinh
     * case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
     * case 2 : tìm thấy khách hàng với id truyền vào -> update vào db
     */
    @Override
    public KhachHangDTO updateKhachHang(Long id, KhachHangRequest khachHangRequest) {
         khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.KH_NOT_FOUND)));

//        khachHang.loadFromDTO(khachHangDTO);
        KhachHang newKhachHang =khachHangRepository.save(MapperUtils.dtoToEntity(khachHangRequest, KhachHang.class));

        return MapperUtils.entityToDTO(newKhachHang, KhachHangDTO.class);

    }
    /**
     * @param id -> id của khách hàng kiểu dữ liệu long
     * case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
     * case 2 : tìm thấy khách hàng với id truyền vào -> delete vào db
     */
    @Override
    public void deleteKhachHang(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.KH_NOT_FOUND)));

        khachHangRepository.delete(khachHang);
    }

    /**
     * @param id -> id của khách hàng kiểu dữ liệu long
     * case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
     * case 2 : tìm thấy khách hàng với id truyền vào -> trả về 1 khachhangDTO
     */
    @Override
    public KhachHangDTO findKhachHangById(Long id) {
        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.KH_NOT_FOUND)));

        return MapperUtils.entityToDTO(khachHang, KhachHangDTO.class);
    }
}
