package com.warden.common.service;

import com.warden.common.entity.Role;
import com.warden.common.entity.User;
import com.warden.common.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author warden
 */
@Service
public class UserService extends BaseService<UserRepository, User, Long> {

    @Cacheable(value = "user:userName", key = "#p0")
    public User getUserByName(String userName) {
        return repository.findByUserName(userName);
    }

    @Cacheable(value = "user:id",key = "#p0")
    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }


    @Override
    public List<User> all() {
        return super.all();
    }

//    @Cacheable(value="user:password", key ="#p0")
    public String getPassword(String username) {
        return repository.findByUserName(username).getPassword();
    }
}
