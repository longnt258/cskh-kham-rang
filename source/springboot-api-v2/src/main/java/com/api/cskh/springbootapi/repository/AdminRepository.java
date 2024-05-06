package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminByUsername(String username);
}
