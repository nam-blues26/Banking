package com.banking.service;

import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import com.banking.entity.User;

public interface IUserService {
     User addUser(UserDTO user);

     TokenDTO login(AuthencationDTO authencationDTO);
}
