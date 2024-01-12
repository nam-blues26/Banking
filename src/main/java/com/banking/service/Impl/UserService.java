package com.banking.service.Impl;

import com.banking.constant.MessageConstant;
import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import com.banking.entity.User;
import com.banking.exception.ExistException;
import com.banking.repository.IRoleRepository;
import com.banking.repository.IUserRepository;
import com.banking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Thêm user
     * Case 1: Trùng username -> error(User đã tồn tại)
     * Case 2: username mới -> mã hóa password, setRole : nguoi dung -> thêm vào db
     *
     * @param user -> thuộc tính id, username, full name, password, role
     * @return user
     */
    @Override
    public User addUser(UserDTO user) {
        Optional<User> userCheck = userRepository.findByUsername(user.getUsername());
        if (userCheck.isPresent()) {
            userCheck.get().setPassword(passwordEncoder.encode(user.getPassword()));
//            userCheck.get().setRole(roleRepo.findById(1).get());
            return userRepository.save(userCheck.get());
        }
        throw new ExistException(MessageConstant.USER_NOT_FOUND);
    }

    @Override
    public TokenDTO login(AuthencationDTO authencationDTO) {
        return null;
    }
    //đăng nhập

    public String deleteUser(Integer id) {
        return userRepository.deleteUser(id);
    }

    /**
     update người dùng theo id
     id -> id của người dùng
     user_name -> tên người dùng mới
     ho_ten -> full name người dùng mới
     case 1: không tìm thấy người dùng theo id truyền vào -> throw "không tìm thấy user"

     pass -> insert vào bảng user
     */
    public User updateUser(Integer id, String userName, String hoTen) {
       int result = userRepository.updateUser(id, userName, hoTen);
       if (result == 2) throw new UsernameNotFoundException(MessageConstant.USER_NOT_FOUND);
        return userRepository.findById(id).get();
    }
}