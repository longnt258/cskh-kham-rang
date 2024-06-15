package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.Dentist;
import com.api.cskh.springbootapi.domain.Schedule;
import com.api.cskh.springbootapi.dto.ScheduleDTO;
import com.api.cskh.springbootapi.repository.DentistRepository;
import com.api.cskh.springbootapi.repository.ScheduleRepository;
import com.api.cskh.springbootapi.repository.UserRepository;
import com.api.cskh.springbootapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final DentistRepository dentistRepository;

    @Override
    public List<ScheduleDTO> findAll() {
        LogUtil.logger.info("Find all Schedule");
        List<ScheduleDTO> scheduleResultList = new LinkedList<>();
        List<Schedule> schedules = scheduleRepository.findAll();
        schedules.sort(Comparator.comparing(Schedule::getCreatedDate).reversed());
        schedules.forEach(s -> {
            scheduleResultList.add(new ScheduleDTO(s));
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

    @Override
    public ScheduleDTO createByAdmin(ScheduleDTO scheduleDTO) {
        LogUtil.logger.info("Create new Schedule");
        try {
            Schedule schedule = new Schedule(scheduleDTO.getTitle(),
                    scheduleDTO.getDescription(),
                    DateUtil.convertString2Date(scheduleDTO.getBookingDatetime()),
                    userRepository.findUserByUserId(scheduleDTO.getUserId()),
                    dentistRepository.findDentistByDentistId(scheduleDTO.getDentistId()));
            return new ScheduleDTO(scheduleRepository.save(schedule), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ScheduleDTO bookingByUser(ScheduleDTO scheduleDTO) {
        LogUtil.logger.info("Create new Schedule by User");
        try {
            List<Dentist> dentists = dentistRepository.findAll().stream().filter(Dentist::getStatus).toList();
            Schedule schedule = new Schedule(scheduleDTO.getTitle(),
                    scheduleDTO.getDescription(),
                    DateUtil.convertString2Date(scheduleDTO.getBookingDatetime()),
                    userRepository.findUserByUserId(scheduleDTO.getUserId()),
                    dentists.get(new Random().nextInt(dentists.size())));
            return new ScheduleDTO(scheduleRepository.save(schedule), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ScheduleDTO update(String scheduleCode, ScheduleDTO inputScheduleUpdate) {
        LogUtil.logger.info("Update Schedule by code: " + scheduleCode);
        try {
            Schedule schedule = scheduleRepository.findScheduleByCode(scheduleCode);
            /* Kiểm tra thông tin tránh bị null */
            if(inputScheduleUpdate.getTitle() != null) {
                schedule.setTitle(inputScheduleUpdate.getTitle());
            }
            if(inputScheduleUpdate.getDescription() != null) {
                schedule.setDescription(inputScheduleUpdate.getDescription());
            }
            if(inputScheduleUpdate.getBookingDatetime() != null) {
                schedule.setBookingDatetime(DateUtil.convertString2Date(inputScheduleUpdate.getBookingDatetime()));
            }
            if(inputScheduleUpdate.getStatus() != null) {
                schedule.setStatus(inputScheduleUpdate.getStatus());
            }
            if(inputScheduleUpdate.getDentistId() != null) {
                schedule.setDentist(dentistRepository.findDentistByDentistId(inputScheduleUpdate.getDentistId()));
            };

            return new ScheduleDTO(scheduleRepository.save(schedule), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
