package com.warden.common.dao;

import com.warden.common.entity.Permission;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：yangjiaying
 * @version ：
 * @date ：Created in 2019/8/18
 * @description ：
 */
@Repository
public interface PermissionDao extends BaseMapper<Permission> {
    /**
     * 获取某用户
     *
     * @param roleId roleId
     * @return permissionList
     */
    List<Permission> getPermissionByRoleId(Long roleId);
}
