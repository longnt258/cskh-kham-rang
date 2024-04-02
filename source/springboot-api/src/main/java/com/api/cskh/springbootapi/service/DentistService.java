package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.domain.Dentist;

import java.util.List;

public interface DentistService {
    List<Dentist> findAll();
}
