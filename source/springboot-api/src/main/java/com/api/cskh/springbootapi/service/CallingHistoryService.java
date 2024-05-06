package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.dto.CallingHistoryDTO;

import java.util.List;

public interface CallingHistoryService {
    List<CallingHistoryDTO> findAll();

    List<CallingHistoryDTO> findByPhoneNumber(String phoneNumber);

    List<CallingHistoryDTO> findByUserId(Integer userId);

    CallingHistoryDTO create(CallingHistoryDTO callingHistoryDTO);

    void updateUser(String phoneNumber);
}
