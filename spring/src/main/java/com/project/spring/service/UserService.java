package com.project.spring.service;

import com.project.spring.Security.JwtUtil;
import com.project.spring.Security.UserConfig;
import com.project.spring.DTO.LoginRequestDto;
import com.project.spring.DTO.SignupRequestDTO;
import com.project.spring.Entity.User;
import com.project.spring.taskrepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private UserConfig config;
    @Autowired
    private JwtUtil jwtutil;

    public User createUser(SignupRequestDTO requestDTO){
       if(userrepo.existsByEmail(requestDTO.getEmail())){
           throw new RuntimeException("Email already Exists");
        }
       User newuser = new User();
       newuser.setName(requestDTO.getName());
       newuser.setEmail(requestDTO.getEmail());
       newuser.setPassword(config.passwordEncoder().encode(requestDTO.getPassword()));

       return userrepo.save(newuser);
    }
    public  String login(LoginRequestDto loginDto){
        User  user = userrepo.findByEmail(loginDto.getEmail())
                .orElseThrow(()->new RuntimeException("User Not Found"));
        if(!config.passwordEncoder().matches(loginDto.getPassword(),user.getPassword())){

            throw new RuntimeException("Incorrect password");
        }
        return jwtutil.generateToken(user.getEmail());

    }
}
