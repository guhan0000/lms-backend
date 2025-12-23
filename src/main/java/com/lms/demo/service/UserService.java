package com.lms.demo.service;

import com.lms.demo.model.User;
import com.lms.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
//    public User testGetUserByEmail(String email){
//
//        Optional<User> byEmail = userRepository.findByEmail(email);
//       if(byEmail.isPresent()){
//           return byEmail.get();
//       }
//       else {
//           log.warn("user not found");
//           throw new RuntimeException("User Not Found");
//
//       }
//
//    }
//    public User testgetUserbyEmail(String email){
//        return userRepository.findByEmail(email);
//    }

}
