package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.Dentist;
import com.api.cskh.springbootapi.repository.DentistRepository;
import com.api.cskh.springbootapi.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {
    private final DentistRepository dentistRepository;

    @Override
    public List<Dentist> findAll() {
        LogUtil.logger.info("Find all Dentist");
        return dentistRepository.findAll();
    }
}
