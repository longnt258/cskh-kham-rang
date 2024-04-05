package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.dto.UserDTO;
import com.api.cskh.springbootapi.dto.UserRegisterDTO;
import com.api.cskh.springbootapi.repository.UserRepository;
import com.api.cskh.springbootapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public UserDTO userRegister(UserDTO userDTO) {
        LogUtil.logger.info("Register User");
        try {
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .fullName(userDTO.getFullName())
                    .email(userDTO.getEmail())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .schedules(new ArrayList<>())
                    .build();
            return new UserDTO(userRepository.save(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
