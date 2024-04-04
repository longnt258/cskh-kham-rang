package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.Schedule;
import com.api.cskh.springbootapi.dto.ScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findScheduleByCode(String code);

    List<Schedule> findScheduleByUserUserId(Integer userId);

    List<Schedule> findScheduleByDentistDentistId(Integer dentistId);
}
