package com.group6.gbac.controller;

import com.group6.gbac.model.User;
import com.group6.gbac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateAcctC implements   Command{

    @Autowired
    UserRepository userRepository;

    @Override
    public void execute() {
        User user = new User();

        userRepository.save(user);
    }
    private String pwd;
    private String userName;

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
