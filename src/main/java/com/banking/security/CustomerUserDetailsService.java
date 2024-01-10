package com.banking.security;

import com.banking.constant.UserConstant;
import com.banking.entity.User;
import com.banking.exception.NotFoundException;
import com.banking.repository.IUserReporitory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerUserDetailsService  implements UserDetailsService {
    @Autowired
    private IUserReporitory iUserReporitory;

    /**
     * Ghi đè lại phương thức findByUsername của UserDetailsService.
     * Gọi đến iUserReporitory trả lại User theo username
     * Tạo CustomUserDetails từ User tìm được
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserReporitory.findByUsername(username).orElseThrow(()
                -> new NotFoundException(UserConstant.USER_NOT_FOUND_BY_NAME + username));
        return new CustomerUserDetails(user);
    }
}
