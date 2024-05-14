package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> findAll();

    ScheduleDTO findByCode(String code);

    List<ScheduleDTO> findByUserId(Integer userId);

    List<ScheduleDTO> findByDentistId(Integer dentistId);

    ScheduleDTO createByAdmin(ScheduleDTO scheduleDTO);

    ScheduleDTO bookingByUser(ScheduleDTO scheduleDTO);

    ScheduleDTO update(String scheduleCode, ScheduleDTO inputScheduleUpdate);
}
