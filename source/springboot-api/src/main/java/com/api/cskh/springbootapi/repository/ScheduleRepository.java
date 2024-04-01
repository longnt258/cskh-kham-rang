package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
