package com.lms.demo.controller;

import com.lms.demo.model.User;
import com.lms.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

@RequestMapping("/api/users")

public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String,String>credentials){
        String token = userService.login(credentials.get("email"), credentials.get("password"));
        return java.util.Collections.singletonMap("token", token);
    }

}
