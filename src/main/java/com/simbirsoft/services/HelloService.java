package com.simbirsoft.services;

import com.simbirsoft.entity.User;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getHello() {
        return "HelloWorld!";
    }

    public User getUser() {
        User user = new User();
        user.setAge(18);
        user.setName("Name");
        return user;
    }

    public String setUser(User user) {
        return "Hello, " + user.getName();
    }

    public String getHelloUser(String name) {
        return "Hello, " + name;
    }
}
