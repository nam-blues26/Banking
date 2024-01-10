package com.banking.repository;

import com.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserReporitory extends JpaRepository<User, Integer> {
    //tìm người dung theo username
    Optional<User>findByUsername(String username);
}
