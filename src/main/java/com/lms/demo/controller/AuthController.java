package com.lms.demo.controller;

import com.lms.demo.model.User;
import com.lms.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/me")
    public User getMe(@RequestHeader("Authorization") String token){
        String email=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByEmail(email);
    }

}
