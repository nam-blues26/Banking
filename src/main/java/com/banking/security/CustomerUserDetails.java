package com.banking.security;

import com.banking.entity.Permission;
import com.banking.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Customer User khi implement UserDetails
 */
public class CustomerUserDetails implements UserDetails {

    private User user;

    public CustomerUserDetails(User user) {
        this.user = user;
    }

    //Ghi đè lại phương thức getAuthorities của UserDetails.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .flatMap(role -> Stream.concat(
                        Stream.of(new SimpleGrantedAuthority( role.getName().toString())),
                        role.getPermissions()
                                .stream()
                                .map(permission -> new SimpleGrantedAuthority(permission.getName().toString()))
                ))
                .collect(Collectors.toList());
    }

    //Ghi đè lại phương thức getPassword của UserDetails.
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    //Ghi đè lại phương thức getUsername của UserDetails.
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    //Ghi đè lại phương thức isAccountNonExpired của UserDetails.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Ghi đè lại phương thức isAccountNonLocked của UserDetails.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Ghi đè lại phương thức isCredentialsNonExpired của UserDetails.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Ghi đè lại phương thức isEnabled của UserDetails.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
