package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.dto.ScheduleDTO;
import com.api.cskh.springbootapi.repository.ScheduleRepository;
import com.api.cskh.springbootapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<ScheduleDTO> findAll() {
        LogUtil.logger.info("Find all Schedule");
        List<ScheduleDTO> scheduleResultList = new LinkedList<>();
        scheduleRepository.findAll().forEach(s -> {
            scheduleResultList.add(new ScheduleDTO(s, 0));
        });

        return scheduleResultList;
    }

    @Override
    public ScheduleDTO findByCode(String code) {
        LogUtil.logger.info("Find Schedule by code");
        ScheduleDTO scheduleDTO = null;
        try {
            scheduleDTO = new ScheduleDTO(scheduleRepository.findScheduleByCode(code), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduleDTO;
    }

    @Override
    public List<ScheduleDTO> findByUserId(Integer userId) {
        LogUtil.logger.info("Find all Schedule of User by userId");
        List<ScheduleDTO> scheduleResultList = new LinkedList<>();
        scheduleRepository.findScheduleByUserUserId(userId).forEach(s -> {
            scheduleResultList.add(new ScheduleDTO(s, 1));
        });

        return scheduleResultList;
    }

    @Override
    public List<ScheduleDTO> findByDentistId(Integer dentistId) {
        LogUtil.logger.info("Find all Schedule of Dentist by dentistId");
        List<ScheduleDTO> scheduleResultList = new LinkedList<>();
        scheduleRepository.findScheduleByDentistDentistId(dentistId).forEach(s -> {
            scheduleResultList.add(new ScheduleDTO(s, 2));
        });

        return scheduleResultList;
    }
}
