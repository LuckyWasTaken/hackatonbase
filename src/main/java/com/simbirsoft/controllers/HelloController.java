package com.simbirsoft.controllers;

import com.simbirsoft.entity.User;
import com.simbirsoft.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private HelloService hello;

    @RequestMapping(value = "/world", method = RequestMethod.GET)
    public String getHello() {
        return hello.getHello();
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public String getHelloUser(@PathVariable String name) {
        return hello.getHelloUser(name);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        return hello.getUser();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String setUser(@RequestBody User user) {
        return hello.setUser(user);
    }
}
