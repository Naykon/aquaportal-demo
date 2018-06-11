package com.example.demo.service;

import com.example.demo.entity.user.Role;
import com.example.demo.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findUser(){
        return roleRepository.findByName("ROLE_USER");
    }

    @Override
    public Role findAdmin() {
        return roleRepository.findByName("ROLE_ADMIN");
    }
}
