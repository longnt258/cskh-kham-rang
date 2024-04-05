package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.dto.*;
import com.api.cskh.springbootapi.service.AdminService;
import com.api.cskh.springbootapi.service.AuthService;
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
    private final AdminService adminService;
    private final AuthService authService;

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
        response.setStatus(0);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<ResponseDTO<AdminDTO>> adminLogin(@RequestBody LoginDTO loginDTO) {
        ResponseDTO<AdminDTO> response = new ResponseDTO<>();
        AdminDTO adminDTO = adminService.findByUsername(loginDTO.getUsername());
        if(adminDTO != null && Objects.equals(loginDTO.getPassword(), adminDTO.getPassword())) {
            response.setData(adminDTO);
            response.setMessage("OK");
            return ResponseEntity.ok(response);
        }
        response.setMessage("Username or password is not corrected!");
        response.setStatus(0);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/user/register")
    public ResponseEntity<ResponseDTO<UserDTO>> userRegister(@RequestBody UserRegisterDTO userRegisterDTO) {
        ResponseDTO<UserDTO> response;
        UserDTO userDTO = null;
        try {
            String message = "OK";
            int status = 1;

            // check input not empty
            if(userRegisterDTO.getFullName() == null || "".equals(userRegisterDTO.getFullName())) {
                userRegisterDTO.setFullName("Unknown");
            }

            if(userRegisterDTO.getUsername() == null || "".equals(userRegisterDTO.getUsername())) {
                message = "Username is empty!";
                status = 0;
            } else if(userRegisterDTO.getPassword() == null || "".equals(userRegisterDTO.getPassword())) {
                message = "Password is empty!";
                status = 0;
            } else if(userRegisterDTO.getPhoneNumber() == null || "".equals(userRegisterDTO.getPhoneNumber())) {
                message = "Phone number is empty!";
                status = 0;
            } else if(userService.findByUsername(userRegisterDTO.getUsername().trim()) != null) {
                message = "Username is existed!";
                status = 0;
            } else if(userService.findByPhoneNumber(userRegisterDTO.getPhoneNumber().trim()) != null) {
                message = "Phone number is existed!";
                status = 0;
            }

            if(status == 1) {
                userDTO = authService.userRegister(new UserDTO(userRegisterDTO));
                if(userDTO == null) {
                    message = "Failure register!";
                    status = 0;
                }
            }
            response = new ResponseDTO<>(userDTO, message, status);
        } catch (Exception e) {
            response = new ResponseDTO<>(null, "Error while register!", 0);
        }

        return ResponseEntity.ok(response);
    }
}
