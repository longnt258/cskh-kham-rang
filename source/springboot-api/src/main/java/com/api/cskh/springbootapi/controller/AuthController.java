package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.dto.LoginDTO;
import com.api.cskh.springbootapi.dto.ResponseDTO;
import com.api.cskh.springbootapi.dto.UserDTO;
import com.api.cskh.springbootapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<UserDTO>> login(@RequestBody LoginDTO loginDTO) {
        ResponseDTO<UserDTO> response = new ResponseDTO<>();
        UserDTO userDTO = userService.findByUsername(loginDTO.getUsername());
        if(userDTO != null && Objects.equals(loginDTO.getPassword(), userDTO.getPassword())) {
            response.setData(userDTO);
            response.setMessage("OK");
            return ResponseEntity.ok(response);
        }

        response.setMessage("Username or password is not corrected!");
        return ResponseEntity.badRequest().body(response);
    }
}
