package com.warden.common.rest;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.warden.common.bean.Response;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;


/**
 * 处理异常
 *
 * @author: YangJiaYing
 * @date: 2018/8/3
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = AccountException.class)
    public Response handelShiroException(Exception e) {
        Response response = new Response();
        return response.setStatus(401).setMessage(e.getMessage());
    }

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Response handle401(HttpServletResponse httpServletResponse, Throwable ex) {
        Response response = new Response();
        httpServletResponse.setStatus(403);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Type","text/html;charset=UTF-8");
        return response.setStatus(403).setMessage("你没有权限访问");
    }

    @ExceptionHandler(AuthenticationException.class)
    public Response handelAuthenticationException(Exception e) {
        Response response = new Response();
        return response.setStatus(401).setMessage(e.getMessage());
    }

    // 捕捉其他所有异常
    @ExceptionHandler(TokenExpiredException.class)
    public Response globalException() {
        Response response = new Response();
        return response.setStatus(401).setMessage("令牌过期");
    }

}
