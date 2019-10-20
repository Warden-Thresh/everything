package com.warden.common.rest;

import com.warden.common.bean.PageResult;
import com.warden.common.bean.Response;
import com.warden.common.biz.UserBiz;
import com.warden.common.service.BaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author yangjiaying
 */
public class BaseController<Entity,Service extends BaseService> {

    @Autowired
    protected Service service;

//    @RequiresRoles("admin")
//    @RequiresPermissions(value = {"page"})
    @GetMapping("/page")
    public Response page(@PageableDefault(direction = Sort.Direction.DESC,sort = "id") Pageable pageable) {
        return Response.success(new PageResult<Entity>(service.page(pageable)));
    }

    @GetMapping("/search")
    public Response search(MultiValueMap<String, String> search) {
        return Response.success(search);
    }

    @GetMapping("/all")
    public Response all() {
        return Response.success(service.all());
    }

    @PostMapping("/add")
    public Response add(@RequestBody Entity entity) {
        return Response.success(service.insert(entity));
    }

    @GetMapping("/delete/{id}")
    public Response delete(@PathVariable String id) {
        service.delete(id);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response delete(String[] key) {
        Arrays.stream(key).forEach(service::delete);
        return Response.success();
    }

    @PostMapping("/update")
    public Response update(@RequestBody Entity entity) {
        System.out.println(entity);
        return Response.success(service.update(entity));
    }
}
