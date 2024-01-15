package com.banking.security;

//import com.banking.security.filter.JwtTokenFilter;

import com.banking.entity.RoleType;
import com.banking.security.filter.JwtTokenFilter;
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

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends Exception {

    @Value("${project.bank.version.v1}")
    private String apiPrefix;

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
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(r -> {
                    r.requestMatchers(GET,String.format("%s/user/detail-user/**", apiPrefix))
                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString(),RoleType.ROLE_USER.toString()); // có quyền ADMIN và USER
                    r.requestMatchers(GET,String.format("%s/user/get-all-user", apiPrefix))
                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
                    r.requestMatchers(POST,String.format("%s/user/**", apiPrefix))
                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
                    r.requestMatchers(PUT,String.format("%s/user/**", apiPrefix))
                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
                    r.requestMatchers(DELETE,String.format("%s/user/**", apiPrefix))
                            .hasAnyAuthority(RoleType.ROLE_ADMIN.toString());                               // có quyền ADMIN
                    r.anyRequest().permitAll();                                                             // những request còn lại không cần quyền
                });
        return http.build();
    }


}
