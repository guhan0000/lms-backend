package com.lms.demo.service;

import com.lms.demo.model.User;
import com.lms.demo.repository.UserRepository;
import com.lms.demo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(!(user.getRole() =="ADMIN")){
            user.setRole("MEMBER");
        }
        return userRepository.save(user);
    }
    public String login(String email, String password){
        Optional<User> userOpt = userRepository.findByEmail(email);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            if (passwordEncoder.matches(password,user.getPassword())){
                return jwtUtil.generateToken(user.getEmail(),user.getRole());
            }
        }

            throw new RuntimeException("Invalid Credentials");


    }

}
