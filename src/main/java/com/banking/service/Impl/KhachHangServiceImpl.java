package com.banking.service.Impl;

import com.banking.constant.MessageConstant;
import com.banking.dto.KhachHangDTO;
import com.banking.dto.KhachHangRequest;
import com.banking.entity.User;
import com.banking.exception.ExistException;
import com.banking.exception.NotFoundException;
import com.banking.entity.KhachHang;
import com.banking.repository.IKhachHangRepository;
import com.banking.service.IKhachHangService;
import com.banking.service.base.IMessageService;
import com.banking.utils.MapperUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @return 1 danh sách khachhangDTO
     */
    @Override
    public List<KhachHang> findAllKhachHang() {
//        return khachHangRepository.findAll().stream().map(KhachHangDTO::loadFromEntity).toList();
        return khachHangRepository.selectAllKhachHangBySP();
    }

    /**
     * @param khachHangRequest với các thuộc tính:
     *                         SDT, CCCD, Hoten, GioiTinh, NgaySinh
     *                         case 1 : trùng cccd sẽ trả về class lỗi CCCDisExistException
     *                         case 2 : cccd mới -> thêm vào db
     */
    @Override
    public KhachHang insertKhachHang(KhachHangRequest khachHangRequest) {
//        Optional<KhachHang> khachHang = khachHangRepository.findKhachHangByCccd(khachHangRequest.getCccd());
//        if (khachHang.isPresent()){
//            throw  new ExistException(messageService.getMessage(MessageConstant.KH_CCCD_EXIST));
//        }else {
//
//            return khachHangRepository.save(MapperUtils.dtoToEntity(khachHangRequest, KhachHang.class));
//        }
            Optional<KhachHang> khachHang= khachHangRepository.themKhachHang(
                    khachHangRequest.getSdt(),
                    khachHangRequest.getCccd(),
                    khachHangRequest.getHoTen(),
                    khachHangRequest.getGioiTinh().name(),
                    khachHangRequest.getNgaySinh());

            return khachHang.get();
    }

    /**
     * @param id               -> id của khách hàng kiểu dữ liệu long
     * @param khachHangRequest -> SDT, CCCD, Hoten, GioiTinh, NgaySinh
     *                         case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
     *                         case 2 : tìm thấy khách hàng với id truyền vào -> update vào db
     */
    @Override
    public KhachHang updateKhachHang(Long id, KhachHangRequest khachHangRequest) {
//        KhachHang khachhang = khachHangRepository.findKhachHangById(id)
//                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.KH_NOT_FOUND)));
//        khachhang.loadFromDTO(khachHangRequest);
//        return khachHangRepository.save(khachhang);
        int result = khachHangRepository.updateKhachHang(id, khachHangRequest.getSdt(),
                khachHangRequest.getCccd(), khachHangRequest.getHoTen()
                , khachHangRequest.getNgaySinh(),khachHangRequest.getGioiTinh().name());
        if (result == 0) throw new UsernameNotFoundException(MessageConstant.USER_NOT_FOUND);
        return khachHangRepository.findKhachHangById(id).get();
    }

    /**
     * @param id -> id của khách hàng kiểu dữ liệu long
     *           case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
     *           case 2 : tìm thấy khách hàng với id truyền vào -> delete vào db
     */
    @Override
    public Boolean deleteKhachHang(Long id) {
//        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
//                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.KH_NOT_FOUND)));
//        khachHangRepository.delete(khachHang);
         Boolean check = khachHangRepository.deleteKhachhang(id);
         if (check == true){
             return true;
         }else {
             return false;
         }
    }

    /**
     * @param id -> id của khách hàng kiểu dữ liệu long
     *           case 1 : Không tìm thấy khách hằng với id truyền vào sẽ trả về class lỗi KhachHangNotFoundException
     *           case 2 : tìm thấy khách hàng với id truyền vào -> trả về 1 khachhangDTO
     *           comment
     */
    @Override
    public KhachHang findKhachHangById(Long id) {
//        KhachHang khachHang = khachHangRepository.findKhachHangById(id)
//                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.KH_NOT_FOUND)));
//
//        return khachHang;

        KhachHang khachHang = khachHangRepository.searchCustomerById(id);

        if (khachHang == null) {
            throw new NotFoundException(messageService.getMessage(MessageConstant.KH_NOT_FOUND));
        }

        return khachHang;
    }
}
