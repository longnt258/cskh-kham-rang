package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.dto.AdminDTO;
import com.api.cskh.springbootapi.repository.AdminRepository;
import com.api.cskh.springbootapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public List<AdminDTO> findAll() {
        LogUtil.logger.info("Find all Admin");
        List<AdminDTO> adminResultList = new LinkedList<>();
        adminRepository.findAll().forEach(ad -> {
            adminResultList.add(new AdminDTO(ad));
        });
        return adminResultList;
    }

    @Override
    public AdminDTO findByUsername(String username) {
        LogUtil.logger.info("Find Admin by username");
        AdminDTO adminDTO = null;
        try {
            adminDTO = new AdminDTO(adminRepository.findAdminByUsername(username));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminDTO;
    }
}
