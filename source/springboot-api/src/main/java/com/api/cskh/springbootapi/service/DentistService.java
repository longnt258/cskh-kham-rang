package com.api.cskh.springbootapi.service;


import com.api.cskh.springbootapi.dto.DentistDTO;

import java.util.List;

public interface DentistService {
    List<DentistDTO> findAll();
}
