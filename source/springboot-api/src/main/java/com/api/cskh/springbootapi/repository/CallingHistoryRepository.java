package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.CallingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallingHistoryRepository extends JpaRepository<CallingHistory, Integer> {
}
