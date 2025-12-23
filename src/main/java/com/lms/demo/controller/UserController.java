package com.lms.demo.controller;

import com.lms.demo.model.User;
import com.lms.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserService userService;
//    @GetMapping("user/")
//    public User testUserByEmail(@RequestParam String email){
//        return userService.testGetUserByEmail(email);
//    }
    @GetMapping("test/")
    public String test(){
        return "test";
    }
}
