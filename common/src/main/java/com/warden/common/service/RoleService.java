package com.warden.common.service;

import com.warden.common.entity.Role;
import com.warden.common.entity.User;
import com.warden.common.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<RoleRepository, Role, Long> {
    @Autowired
    RoleRepository repository;
    @Autowired
    UserService userService;

    @Cacheable(value = "getAllRole")
    @Override
    public List<Role> all() {
        return super.all();
    }

    @Cacheable(value = "role",key = "#p0")
    public Role getByName(String roleName) {
        return repository.findByRoleName(roleName);
    }
}
