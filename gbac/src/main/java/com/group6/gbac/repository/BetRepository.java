package com.group6.gbac.repository;

import com.group6.gbac.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet,Integer> {

     List<Bet> findByUserId(Integer userId);
}
