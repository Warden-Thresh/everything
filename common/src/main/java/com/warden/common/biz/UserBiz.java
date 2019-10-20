package com.warden.common.biz;

import com.warden.common.dao.UserDao;
import com.warden.common.entity.Role;
import com.warden.common.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YangJiaYing
 * @date 2019/05/19
 */
@Service
public class UserBiz extends BaseBiz<UserDao, User> {

    
    @Cacheable(value = "allUser")
    public List<User> getAllUser() {
        return mapper.all();
    }


    @Cacheable(value = "user:id" ,key = "#p0")
    public List<Role> getRoleByUserId(Long userId) {
        return mapper.getRoleByUserId(userId);
    }

    @Cacheable(value = "user:id" ,key = "#p0")
    public User getUserById(Long userId) {
        return mapper.single(userId);
    }
}
