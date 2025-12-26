package com.lms.demo.config;

import com.lms.demo.model.User;
import com.lms.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataSeeder {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedData(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(!userRepository.findByEmail("admin@gmail.com").isPresent()){
                User admin=new User();
                admin.setName("admin");
                admin.setRole("ADMIN");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                userRepository.save(admin);
                System.out.println("Admin user seeded: admin@test.com / admin");

            }
        };
    }
}
