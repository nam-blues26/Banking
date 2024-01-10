package com.banking.repository;

import com.banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    /**
     * Tìm user theo Username
     * @param name -> Username của user
     * @return 1 user
     */
    Optional<User> findByUsername(String name);
}
