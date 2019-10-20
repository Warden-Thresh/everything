package com.warden.common.repository;

import com.warden.common.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YangJiaYing
 * @date 2019/05/16
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
