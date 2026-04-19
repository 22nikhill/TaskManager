package com.project.spring.service;

import com.project.spring.Config.UserConfig;
import com.project.spring.DTO.LoginRequestDto;
import com.project.spring.DTO.SignupRequestDTO;
import com.project.spring.Entity.User;
import com.project.spring.taskrepo.TaskRepo;
import com.project.spring.taskrepo.UserRepo;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private UserConfig config;

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
    public  User login(LoginRequestDto loginDto){
        User  user = userrepo.findByEmail(loginDto.getEmail())
                .orElseThrow(()->new RuntimeException("User Not Found"));
        if(!config.passwordEncoder().matches(loginDto.getPassword(),user.getPassword())){
            System.out.println(user.getPassword());
            System.out.println(loginDto.getPassword());
            throw new RuntimeException(

            );
        }
        return user;

    }
}
