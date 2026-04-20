package com.project.spring.controller;

import com.project.spring.DTO.LoginRequestDto;
import com.project.spring.DTO.SignupRequestDTO;
import com.project.spring.Entity.User;
import com.project.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> create(@Valid @RequestBody SignupRequestDTO singnupDto){
       User newuser = userService.createUser(singnupDto);
       return ResponseEntity.ok(newuser);
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@Valid @RequestBody LoginRequestDto loginDto){
        String  token  = userService.login(loginDto);
        return ResponseEntity.ok(Map.of("token",token));
    }

}
