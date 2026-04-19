package com.project.spring.controller;

import com.project.spring.DTO.SignupRequestDTO;
import com.project.spring.Entity.User;
import com.project.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> create(@Valid @RequestBody SignupRequestDTO singnupDto){
       User newuser = userService.createUser(singnupDto);
       return ResponseEntity.ok(newuser);
    }

}
