package com.banking.service;

import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import com.banking.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserService {
    User addUser(UserDTO user);

    TokenDTO login(AuthencationDTO authencationDTO);

    User updateUser(Integer id, UserDTO userDTO);

    void deleteUser(Integer id);

    List<User> viewAllUser();

    User userDetail(Integer id);
}
