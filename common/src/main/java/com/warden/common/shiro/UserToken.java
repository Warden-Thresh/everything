package com.warden.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 加入登录类型，判断是微信登陆还是普通登陆
 *
 * @author: YangJiaYing
 * @date: 2018/8/6
 */
public class UserToken extends UsernamePasswordToken {


    private LoginType loginType;
    private String openid;

    public UserToken(final String username, final String password, String openid, LoginType loginType) {
        super(username, password);
        this.loginType = loginType;
        this.openid = openid;
    }

    public UserToken(final String username, final String password, LoginType loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getOpenid() {
        return openid;
    }
}