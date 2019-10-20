package com.warden.common;

import com.warden.common.entity.User;
import com.warden.common.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        System.out.println(userRepository.findByUserName("A"));
    }

    @Test
    public void addUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUserName(UUID.randomUUID().toString());
            list.add(user);
        }
        userRepository.saveAll(list);
    }
}
