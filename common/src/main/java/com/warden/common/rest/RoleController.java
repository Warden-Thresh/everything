package com.warden.common.rest;

import com.warden.common.bean.Response;
import com.warden.common.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warden
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<RoleController, RoleService> {

    @Autowired
    RoleService roleService;

    @GetMapping("/name")
    public Response getRoleByName(String roleName) {
        return Response.success(roleService.getByName(roleName));
    }

}
