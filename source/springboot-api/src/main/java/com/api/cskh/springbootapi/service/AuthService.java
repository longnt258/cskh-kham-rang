package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.dto.UserDTO;

public interface AuthService {
    UserDTO userRegister(UserDTO userDTO);
}
