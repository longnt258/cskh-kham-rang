package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.repository.UserRepository;
import com.api.cskh.springbootapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        LogUtil.logger.info("Find all User");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        LogUtil.logger.info("Find User by id");
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        LogUtil.logger.info("Find User by username");
        return userRepository.findByUsername(username);
    }
}
