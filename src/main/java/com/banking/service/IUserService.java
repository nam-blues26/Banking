package com.banking.service;

import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import com.banking.entity.User;
import org.springframework.data.repository.query.Param;

public interface IUserService {
     User addUser(UserDTO user);
     String deleteUser(Integer id);
     User updateUser(Integer id,String userName,String hoTen);

     TokenDTO login(AuthencationDTO authencationDTO);
}
