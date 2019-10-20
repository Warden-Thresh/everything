package com.warden.common.rest;

import com.warden.common.bean.AuthResponse;
import com.warden.common.bean.Response;
import com.warden.common.entity.User;
import com.warden.common.service.UserService;
import com.warden.common.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author YangJiaYing
 * @date 2019/05/16
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        User relUser = userService.getUserByName(user.getUserName());
        String password = user.getPassword();
        System.out.println(user);
        return Optional.ofNullable(relUser).map(u -> {
            if (u.getPassword().equals(password)) {
                AuthResponse authResponse = new AuthResponse();
                authResponse.setToken(JWTUtil.createToken(relUser.getUserName(),relUser.getId()));
                return Response.success(authResponse);
            } else {
                return Response.failure(401, "密码错误");
            }
        }).orElse(
                Response.failure(401, "用户不存在")
        );
    }
}

