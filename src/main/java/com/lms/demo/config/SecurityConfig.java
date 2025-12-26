package com.lms.demo.config;

import com.lms.demo.model.User;
import com.lms.demo.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private JwtUtil jwtUtil;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/users/register","/api/users/login")
                        .permitAll()
                        .requestMatchers("/api/books/add","/api/books/delete/**","/api/books/update/**","/api/issues/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/issues/**")
                        .authenticated().anyRequest()
                        .authenticated())
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRwquestFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }
    @Bean
    public OncePerRequestFilter jwtRwquestFilter(){
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                final  String authorizedHeader=request.getHeader("Authorization");
                String userName=null;
                String jwt=null;
                if(authorizedHeader!=null && authorizedHeader.startsWith("Bearer ")){
                    jwt=authorizedHeader.substring(7);
                    try{
                        userName=jwtUtil.extractUserName(jwt);
                    }catch (Exception e){}

                }
                if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                    if(jwtUtil.validateToken(jwt,userName)){
                        String role=jwtUtil.extractRole(jwt);
                        List<SimpleGrantedAuthority> authorities = Collections
                                .singletonList(new SimpleGrantedAuthority("ROLE_" + role));
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userName, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filterChain.doFilter(request,response);
            }
        };
    }

}
//,"/apireturn/**"