package com.warden.common.service;

import com.warden.common.entity.Permission;
import com.warden.common.repository.PermissionRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangJiaYing
 * @date 2019/05/16
 */
@Service
public class PermissionService extends BaseService<PermissionRepository, Permission, Long> {
    @Override
    @Cacheable(value = "getAllPermission")
    public List<Permission> all() {
        return super.all();
    }
}
