package com.banking.security;

//import com.banking.security.filter.JwtTokenFilter;

import com.banking.entity.Role;
import com.banking.entity.RoleType;
import com.banking.security.filter.JwtTokenFilter;
import com.banking.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends Exception {

    private static List<Role> roleList;

    @Value("${project.bank.version.v1}")
    private String apiPrefix;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private CustomerUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean để dùng quản lý các người dùng đã đăng nhập
     *
     * @param config AuthenticationManager.class
     * @return AuthenticationManager object bean
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Cấu hình AuthenticationProvider trong Spring Security giúp xác thực người dùng bằng cách
     * sử dụng UserDetailsService và PasswordEncoder
     */
    @Bean
    protected AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Bean cho SecurityFilterChain
     * Sử dụng SecurityFilterChain cấu hình cho Spring Security
     * Bao gồm phân quyền các endpoint
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        roleList = roleService.getAll();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(r -> {
                    roleList.forEach(role -> {
                        role.getPermissions().forEach(permission -> {
                            r.requestMatchers(permission.getMethod().toString(), String.format("%s%s", apiPrefix, permission.getEndPoint()))
                                    .hasAnyAuthority(permission.getName().toString()); // có quyền ADMIN và USER
                        });
                    });
//                    r.requestMatchers(GET,String.format("%s/user/detail-user/**", apiPrefix))
//                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString(),RoleType.ROLE_USER.toString()); // có quyền ADMIN và USER
//                    r.requestMatchers(GET,String.format("%s/user/get-all-user", apiPrefix))
//                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
//                    r.requestMatchers(POST,String.format("%s/user/**", apiPrefix))
//                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
//                    r.requestMatchers(PUT,String.format("%s/user/**", apiPrefix))
//                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
//                    r.requestMatchers(DELETE,String.format("%s/user/**", apiPrefix))
//                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
                    r.anyRequest().permitAll();                                                             // những request còn lại không cần quyền
                });
        return http.build();
    }

}
