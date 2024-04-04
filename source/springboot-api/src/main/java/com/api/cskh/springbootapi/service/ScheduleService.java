package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> findAll();

    ScheduleDTO findByCode(String code);

    List<ScheduleDTO> findByUserId(Integer userId);

    List<ScheduleDTO> findByDentistId(Integer dentistId);
}
