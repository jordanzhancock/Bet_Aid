package com.group6.gbac.controller;

import com.group6.gbac.model.GBACUser;
import com.group6.gbac.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity<List<GBACUser>> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<GBACUser> createUser(@RequestBody GBACUser user){
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody GBACUser user){
        GBACUser foundUser = userRepository.findByUsername(user.getUsername());
        if(foundUser != null && foundUser.getPassword().equals( user.getPassword())){
            return new ResponseEntity<>("userLoggedIn",HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);

    }

}
