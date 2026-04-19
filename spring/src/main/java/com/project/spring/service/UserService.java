package com.project.spring.service;

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

    public User createUser(SignupRequestDTO requestDTO){
       if(userrepo.existsByEmail(requestDTO.getEmail())){
           throw new RuntimeException("Email already Exists");
        }
       User newuser = new User();
       newuser.setName(requestDTO.getName());
       newuser.setEmail(requestDTO.getEmail());
       newuser.setPassword(requestDTO.getPassword());

       return userrepo.save(newuser);
    }
}
