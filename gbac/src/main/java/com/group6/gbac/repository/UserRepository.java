package com.group6.gbac.repository;

import com.group6.gbac.model.GBACUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<GBACUser, Integer> {
    GBACUser findByUsername(String username);
    // List<User> findAll();
}
