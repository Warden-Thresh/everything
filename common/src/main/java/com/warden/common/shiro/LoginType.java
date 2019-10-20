package com.warden.common.shiro;

/**
 * @author: YangJiaYing
 * @date: 2018/8/6
 */
public enum LoginType {
    /**
     * WEB,普通登陆，WeChat 使用微信登陆
     */
    DEFAULT("Default"), WeChat("WeChat");

    private String type;

    LoginType(String type) {
        if (type == null) {
            this.type = "Default";
        } else {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return this.type;
    }
}
