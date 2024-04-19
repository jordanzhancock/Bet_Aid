package com.group6.gbac.controller;

import com.group6.gbac.model.Authorization;
import com.group6.gbac.model.GBACUser;
import com.group6.gbac.repository.UserRepository;
import com.group6.gbac.service.EmailService;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity<List<GBACUser>> getAllUsers(@RequestBody String string){
        JSONObject obj = new JSONObject(string);
        String accessToken = obj.getString("accessToken");
        UUID token = UUID.fromString(accessToken);
        if(Authorization.validateToken(token)) {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/user")
    public ResponseEntity<GBACUser> createUser(@RequestBody GBACUser user, UUID accessToken){
        if(Authorization.validateToken(accessToken)) {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody GBACUser user){
        GBACUser foundUser = userRepository.findByUsername(user.getUsername());
        if(foundUser != null && foundUser.getPassword().equals( user.getPassword())){
            UUID accessToken = Authorization.createToken();
            return new ResponseEntity<>(accessToken ,HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestBody String string){
        System.out.println(string);
        JSONObject obj = new JSONObject(string);
        String accessToken = obj.getString("accessToken");
        UUID token = UUID.fromString(accessToken);
        if(Authorization.removeToken(token)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
    @Autowired
    private EmailService emailService;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody String email) {
        // Logic to generate new password
        String newPassword = "generatedPassword";

        // Send email
        emailService.sendForgotPasswordEmail(email, newPassword);

        return "Password reset instructions sent to your email.";
    }
}
