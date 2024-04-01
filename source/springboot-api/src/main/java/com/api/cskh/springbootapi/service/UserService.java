package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Integer id);

    User findByUsername(String username);

}
