package com.banking.repository;

import com.banking.entity.Role;
import com.banking.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

    /**
     *
     * @param roleName giá trị trong RoleType
     * @return Optional<Role> -> tránh exception null pointer khi không tìm thấy role
     */
    Optional<Role> findByName(RoleType roleName);
}
