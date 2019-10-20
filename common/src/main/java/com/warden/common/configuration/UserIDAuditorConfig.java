package com.warden.common.configuration;

import com.warden.common.biz.UserBiz;
import com.warden.common.entity.User;
import com.warden.common.service.UserService;
import com.warden.common.utils.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yangjiaying
 */
@Component
@Log4j2
public class UserIDAuditorConfig implements AuditorAware<User> {
    private final UserBiz userBiz;

    @Autowired
    public UserIDAuditorConfig(UserBiz userBiz) {
        this.userBiz = userBiz;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Optional<User> user;
        try {
            String token = SecurityUtils.getSubject().getPrincipal().toString();
            user = Optional.of(userBiz.getUserById(JWTUtil.getUserId(token)));
        } catch (Exception e) {
            return Optional.empty();
        }
        return user;
    }
}
