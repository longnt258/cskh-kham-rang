package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.dto.UserDTO;
import com.api.cskh.springbootapi.repository.UserRepository;
import com.api.cskh.springbootapi.service.AuthService;
import com.api.cskh.springbootapi.service.CallingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CallingHistoryService callingHistoryService;

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
            UserDTO userDTORes =  new UserDTO(userRepository.save(user));
            /* Sau khi tạo tài khoản, kiểm tra lịch sử cuộc gọi và cập nhật để hiển thị tên user trong lịch sử */
            callingHistoryService.updateUser(userDTORes.getPhoneNumber());
            return userDTORes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
