package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.common.Constants;
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
        return ResponseEntity.ok(new ResponseDTO<>(userService.findAll(), Constants.OK, 1));
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseDTO<UserDTO>> findByPhone(@RequestParam String phoneNumber) {
        UserDTO userDTO = userService.findByPhoneNumber(phoneNumber);
        if(userDTO == null) {
            return ResponseEntity.ok(new ResponseDTO<>(null, Constants.NOT_FOUND("Phone number"), 0));
        }
        return ResponseEntity.ok(new ResponseDTO<>(userDTO, Constants.OK, 1));
    }
}
