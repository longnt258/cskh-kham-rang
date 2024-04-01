package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    User findByUsername(String username);
}
