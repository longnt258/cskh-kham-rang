package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(Integer id);

    User findByUsername(String username);

    User findByPhoneNumber(String phoneNumber);
}
