package com.warden.common.shiro;

/**
 * @author YangJiaYing
 * @project assistant_server
 * @date 2018/08/11
 */
public interface baseToken {
    LoginType loginType = LoginType.DEFAULT;

    public LoginType getLoginType();

    public void setLoginType(LoginType loginType);
}
