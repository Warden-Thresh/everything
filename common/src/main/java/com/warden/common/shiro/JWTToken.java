package com.warden.common.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @project: assistant_server
 * @author: YangJiaYing
 * @date: 2018/08/09
 */
public class JWTToken implements AuthenticationToken {
    private String token;
    private LoginType loginType = LoginType.DEFAULT;

    public JWTToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
