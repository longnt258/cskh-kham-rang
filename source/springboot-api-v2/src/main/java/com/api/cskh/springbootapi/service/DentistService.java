package com.api.cskh.springbootapi.service;


import com.api.cskh.springbootapi.dto.DentistDTO;

import java.util.List;

public interface DentistService {
    List<DentistDTO> findAll();

    DentistDTO findById(Integer dentistId);

    DentistDTO createByAdmin(DentistDTO dentistDTO);

    DentistDTO update(Integer dentistId, DentistDTO dentistDTO);
}
