package com.example.jwt.service.Impl;

import com.example.jwt.model.Role;
import com.example.jwt.model.RoleName;
import com.example.jwt.repository.RoleRepository;
import com.example.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Optional<Role> findByRole(RoleName roleName) {
        return roleRepository.findByRole(roleName);
    }
}
