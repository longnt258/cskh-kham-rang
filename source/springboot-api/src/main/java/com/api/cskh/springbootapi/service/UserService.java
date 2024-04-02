package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();

    Optional<User> findById(Integer id);

    UserDTO findByUsername(String username);
}
