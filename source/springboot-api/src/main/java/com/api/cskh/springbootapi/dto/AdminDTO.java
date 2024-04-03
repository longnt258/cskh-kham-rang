package com.api.cskh.springbootapi.dto;

import com.api.cskh.springbootapi.domain.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;

    public AdminDTO(Admin admin) {
        username = admin.getUsername();
        password = admin.getPassword();
        fullName = admin.getFullName();
        email = admin.getEmail();
    }
}
