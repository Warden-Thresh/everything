package com.warden.common.rest;

import com.warden.common.entity.Permission;
import com.warden.common.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJiaYing
 * @date 2019/05/16
 */
@RestController
@RequestMapping("/permission")
public class PermissionControl extends BaseController<Permission,PermissionService> {
    @Autowired
    PermissionService permissionService;
}
