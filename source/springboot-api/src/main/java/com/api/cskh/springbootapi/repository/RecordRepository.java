package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Integer> {
}
