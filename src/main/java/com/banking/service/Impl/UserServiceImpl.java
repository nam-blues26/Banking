package com.banking.service.Impl;

import com.banking.constant.MessageConstant;
import com.banking.dto.AuthencationDTO;
import com.banking.dto.TokenDTO;
import com.banking.dto.UserDTO;
import com.banking.entity.Role;
import com.banking.entity.RoleType;
import com.banking.entity.User;
import com.banking.exception.DOBException;
import com.banking.exception.ExistException;
import com.banking.exception.NotFoundException;
import com.banking.exception.UnauthorizedException;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        this.checkDOB(userDTO.getNgaySinh());

        List<Role> roles = Arrays.asList(roleRepository.findByName(RoleType.ROLE_USER).get());
        User user = MapperUtils.dtoToEntity(userDTO, User.class);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    /**
     * @param authencationDTO : username, password
     * @return Thông tin người dùng và token
     */
    @Override
    public TokenDTO login(AuthencationDTO authencationDTO) {
        User user = userRepository.findByUsername(authencationDTO.getUsername())
                .orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.USER_NOT_FOUND)));
        if (!passwordEncoder.matches(authencationDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(messageService.getMessage(MessageConstant.LOGIN_FAILL));
        }
        CustomerUserDetails customerUserDetail = new CustomerUserDetails(user);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authencationDTO.getUsername(), authencationDTO.getPassword(), customerUserDetail.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        return new TokenDTO(jwtService.generateToken(authencationDTO.getUsername()));
    }

    /**
     * Update user theo id
     *
     * @param userDTO gồm thông tin: username, fullname, password, ngaysinh
     * @return User
     */
    @Override
    public User updateUser(Integer id, UserDTO userDTO) {
        this.checkDOB(userDTO.getNgaySinh());
        User user = User.builder()
                .id(id)
                .fullName(userDTO.getFullName())
                .username(userDTO.getUsername())
                .ngaySinh(userDTO.getNgaySinh())
                .build();
        return userRepository.save(user);
    }

    /**
     * Delete user theo id
     *
     * @param id -> id của user
     */
    @Override
    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent())
            userRepository.deleteById(id);
        throw new NotFoundException(MessageConstant.USER_NOT_FOUND);
    }

    @Override
    public List<User> viewAllUser() {
        return userRepository.findAll();
    }

    /**
     *
     * @param id -> Truyền vào id của User kiểu dữ liệu Integer
     *           case1: Truyền id nếu tìm thấy user trả về 1 User
     *           case2: Truyền id nếu không tìm thấy id trả về 1 class exception NotFoundException
     * @return trả về 1 User
     */
    @Override
    public User userDetail(Integer id) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userRepository.findById(id).
                orElseThrow(() -> new NotFoundException(messageService.getMessage(MessageConstant.USER_NOT_FOUND)));
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(user.getUsername())){
           throw new UnauthorizedException(messageService.getMessage(MessageConstant.USER_UNAUTHORIZED));
        }
        return user;
    }

    /**
     * Validate ngày sinh
     * @param dob Biến có kiểu LocalDate
     */
    public void checkDOB(LocalDate dob){
        LocalDate currentDate = LocalDate.now();
        LocalDate currentDateMinus18years = currentDate.minusYears(18);
        if (dob.isAfter(currentDateMinus18years)){
            throw new DOBException(messageService.getMessage(MessageConstant.USER_DOB_INVALID));
        }
    }
}
