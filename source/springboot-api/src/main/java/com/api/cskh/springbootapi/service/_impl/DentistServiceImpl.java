package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.dto.DentistDTO;
import com.api.cskh.springbootapi.repository.DentistRepository;
import com.api.cskh.springbootapi.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {
    private final DentistRepository dentistRepository;

    @Override
    public List<DentistDTO> findAll() {
        LogUtil.logger.info("Find all Dentist");
        return dentistRepository.findAll()
                .stream().map(DentistDTO::new)
                .collect(Collectors.toList());
    }
}
