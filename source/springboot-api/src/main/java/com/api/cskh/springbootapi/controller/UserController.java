package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }
}
