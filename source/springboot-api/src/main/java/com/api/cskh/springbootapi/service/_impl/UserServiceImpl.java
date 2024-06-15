package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.dto.UserDTO;
import com.api.cskh.springbootapi.repository.UserRepository;
import com.api.cskh.springbootapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        LogUtil.logger.info("Find all User");
        List<UserDTO> userResultList = new LinkedList<>();
        List<User> users =  userRepository.findAll();
        userRepository.findAll().sort(Comparator.comparing(User::getCreatedDate).reversed());

        users.forEach(u -> {
           userResultList.add(new UserDTO(u));
        });
        return userResultList;
    }

    @Override
    public UserDTO findById(Integer id) {
        LogUtil.logger.info("Find User by id");
        UserDTO userDTO = null;
        try {
            userDTO = new UserDTO(userRepository.findUserByUserId(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    @Override
    public UserDTO findByUsername(String username) {
        LogUtil.logger.info("Find User by username");
        UserDTO userDTO = null;
        try {
            userDTO = new UserDTO(userRepository.findByUsername(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    @Override
    public UserDTO findByPhoneNumber(String phoneNumber) {
        LogUtil.logger.info("Find User by phone number");
        UserDTO userDTO = null;
        try {
            userDTO = new UserDTO(userRepository.findByPhoneNumber(phoneNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTO;
    }
}
