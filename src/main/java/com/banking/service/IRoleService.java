package com.banking.service;

import com.banking.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;



public interface IRoleService {
    public List<Role> getAll();
}
