package com.warden.common.repository;

import com.warden.common.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
