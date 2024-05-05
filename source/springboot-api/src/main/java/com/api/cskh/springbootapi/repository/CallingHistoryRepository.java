package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.CallingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallingHistoryRepository extends JpaRepository<CallingHistory, Integer> {
    List<CallingHistory> findCallingHistoriesByPhoneNumber(String phoneNumber);

    List<CallingHistory> findCallingHistoriesByUserUserId(Integer userId);
}
