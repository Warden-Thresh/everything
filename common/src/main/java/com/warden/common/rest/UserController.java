package com.warden.common.rest;

import com.warden.common.dao.UserDao;
import com.warden.common.bean.Response;
import com.warden.common.biz.UserBiz;
import com.warden.common.entity.User;
import com.warden.common.service.UserService;
import com.warden.common.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {
    @Autowired
    UserService userService;
    @Autowired
    UserBiz userBiz;
    @Autowired
    UserDao userDao;
    @GetMapping("/name")
    public Response getRoleByName(String username) {
        return Response.success(userDao.sample(username));
    }


//    @GetMapping("/page")
//    public Response getUserPage(int page, int size) {
//        return Response.success(userDao.pageQueryByCondition(page, size, "user"));
//    }

    @GetMapping("/currentUser")
    public Response currentUser() {
        User user;
        try {
            Long userId = JWTUtil.getUserId(SecurityUtils.getSubject().getPrincipal().toString());
            user = userService.getUserById(userId).get();
            System.out.println(user.getRole());
        } catch (Exception e) {
            return Response.failure(401, "获取用户信息失败");
        }
        return Response.success(user);
    }
}
