package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.dto.ResponseDTO;
import com.api.cskh.springbootapi.dto.UserDTO;
import com.api.cskh.springbootapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<ResponseDTO<List<UserDTO>>> findAllUser() {
        return ResponseEntity.ok(new ResponseDTO<>(userService.findAll(), "OK"));
    }

}
