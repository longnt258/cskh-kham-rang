package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();

    UserDTO findById(Integer id);

    UserDTO findByUsername(String username);

    UserDTO findByPhoneNumber(String phoneNumber);
}
