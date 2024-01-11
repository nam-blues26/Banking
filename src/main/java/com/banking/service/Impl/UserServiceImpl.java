package com.banking.service.Impl;

import com.banking.constant.MessageConstant;
import com.banking.constant.AuthConstant;
import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import com.banking.entity.Role;
import com.banking.entity.RoleType;
import com.banking.entity.User;
import com.banking.exception.ExistException;
import com.banking.exception.NotFoundException;
import com.banking.repository.IRoleRepository;
import com.banking.repository.IUserRepository;
import com.banking.security.CustomerUserDetails;
import com.banking.security.JwtService;
import com.banking.service.IUserService;
import com.banking.service.base.IMessageService;
import com.banking.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IMessageService messageService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Thêm user
     * Case 1: Trùng username -> error(User đã tồn tại)
     * Case 2: username mới -> mã hóa password, setRole : nguoi dung -> thêm vào db
     *
     * @param userDTO -> thuộc tính id, username, full name, password, role
     * @return user
     */
    @Override
    public User addUser(UserDTO userDTO) {
        Optional<User> userCheck = userRepository.findByUsername(userDTO.getUsername());
        if (userCheck.isPresent()) {
            throw new ExistException(messageService.getMessage(MessageConstant.USER_EXIST));
        }

        List<Role> roles = Arrays.asList(roleRepository.findByName(RoleType.ROLE_USER).get());
        User user = MapperUtils.dtoToEntity(userDTO, User.class);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    /**
     *
     * @param authencationDTO : username, password
     * @return Thông tin người dùng và token
     */
    @Override
    public TokenDTO login(AuthencationDTO authencationDTO) {
        User user = userRepository.findByUsername(authencationDTO.getUsername())
                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.USER_NOT_FOUND)));
        if (!passwordEncoder.matches(authencationDTO.getPassword(), user.getPassword())){
            throw new BadCredentialsException(messageService.getMessage(MessageConstant.LOGIN_FAILL));
        }
        CustomerUserDetails customerUserDetail = new CustomerUserDetails(user);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authencationDTO.getUsername(), authencationDTO.getPassword(), customerUserDetail.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        return new TokenDTO(user, jwtService.generateToken(authencationDTO.getUsername()));
    }
}
