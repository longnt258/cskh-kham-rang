package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository extends JpaRepository<Dentist, Integer> {
    Dentist findDentistByDentistId(Integer dentistId);
}
