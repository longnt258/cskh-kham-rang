package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> findAll();

    AdminDTO findByUsername(String username);
}
