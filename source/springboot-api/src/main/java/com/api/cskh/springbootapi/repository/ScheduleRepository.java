package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findScheduleByCode(String code);

    List<Schedule> findScheduleByUserUserId(Integer userId);

    List<Schedule> findScheduleByDentistDentistId(Integer dentistId);

    List<Schedule> findScheduleByStatusAndNotif(Integer status, boolean notif);
}
