package com.group6.gbac.controller;

import com.group6.gbac.model.Bet;
import com.group6.gbac.model.GBACUser;
import com.group6.gbac.repository.BetRepository;
import com.group6.gbac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BetController {
    @Autowired
    BetRepository betRepository;
    @GetMapping("/bets")
    public ResponseEntity<List<Bet>> getAllUsers(){
        return new ResponseEntity<>(betRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/bet")
    public ResponseEntity<Bet> createBet(@RequestBody Bet bet){
        Bet newBet = betRepository.save(bet);
        System.out.println(bet);
        System.out.println(newBet);
        return new ResponseEntity<>(newBet,HttpStatus.OK);
    }

    @GetMapping("/bet/history/{userId}")
    public ResponseEntity<List<Bet>> getAllUsers(@PathVariable Integer userId){
        betRepository.findByUserId(userId);
        return new ResponseEntity<>(betRepository.findAll(), HttpStatus.OK);
    }
}
