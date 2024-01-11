package com.banking.repository;

import com.banking.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission,Long> {
}
