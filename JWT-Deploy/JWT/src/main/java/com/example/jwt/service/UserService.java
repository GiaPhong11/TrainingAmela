package com.example.jwt.service;

import com.example.jwt.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    <S extends User> S save(S entity);
}
