package com.api.cskh.springbootapi.service._impl;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.domain.CallingHistory;
import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.dto.CallingHistoryDTO;
import com.api.cskh.springbootapi.repository.CallingHistoryRepository;
import com.api.cskh.springbootapi.repository.UserRepository;
import com.api.cskh.springbootapi.service.CallingHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CallingHistoryServiceImpl implements CallingHistoryService {
    private final CallingHistoryRepository callingHistoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<CallingHistoryDTO> findAll() {
        LogUtil.logger.info("Find all CallingHistory");
        return callingHistoryRepository.findAll()
                .stream().map(CallingHistoryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CallingHistoryDTO> findByPhoneNumber(String phoneNumber) {
        LogUtil.logger.info("Find CallingHistories by phone number");
        return callingHistoryRepository.findCallingHistoriesByPhoneNumber(phoneNumber)
                .stream().map(CallingHistoryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CallingHistoryDTO create(CallingHistoryDTO callingHistoryDTO) {
        LogUtil.logger.info("Create new CallingHistory");
        try {
            // check phone number
            User user = userRepository.findByPhoneNumber(callingHistoryDTO.getPhoneNumber());
            Date endDate = callingHistoryDTO.getEndDate() != null ? DateUtil.convertString2Date(callingHistoryDTO.getEndDate()) : null;

            CallingHistory callingHistory = CallingHistory.builder()
                    .phoneNumber(callingHistoryDTO.getPhoneNumber())
                    .status(callingHistoryDTO.isStatus())
                    .description(callingHistoryDTO.getDescription())
                    .startDatetime(DateUtil.convertString2Date(callingHistoryDTO.getStartDate()))
                    .endDatetime(endDate)
                    .user(user)
                    .build();
            return new CallingHistoryDTO(callingHistoryRepository.save(callingHistory));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(String phoneNumber) {
        LogUtil.logger.info("Update user for CallingHistory");
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if(user == null) {
            return;
        }

        List<CallingHistory> callingHistories = callingHistoryRepository.findCallingHistoriesByPhoneNumber(phoneNumber);
        for(CallingHistory c : callingHistories) {
            c.setUser(user);
        }
        callingHistoryRepository.saveAll(callingHistories);
    }
}
