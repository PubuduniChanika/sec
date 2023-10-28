package com.example.security.controller;

import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
@CrossOrigin
public class LoginController {
    @Autowired
    UserRepository userRepository;
@Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        ResponseEntity response = null;
        try {

            String hashPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPassword);
            User savedUser = userRepository.save(user);
            if (savedUser.getUserId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("saved");
            }
        }catch(Exception ex){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Exception "+ex.getMessage());
            }
        return response;
    }

}
