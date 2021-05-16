package com.warden.common.biz;

import com.warden.common.dao.PermissionDao;
import com.warden.common.entity.Permission;

import java.util.List;

/**
 * @author ：yangjiaying
 * @version ：
 * @date ：Created in 2019/8/18
 * @description ：
 */
public class PermissionBiz extends BaseBiz<PermissionDao, Permission> {
    public List<Permission> getPermissionByRoleId(Long roleId) {
        return mapper.getPermissionByRoleId(roleId);
    }
}
