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

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
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
        List<CallingHistoryDTO> cHResultList = new LinkedList<>();
        List<CallingHistory> callingHistories = callingHistoryRepository.findAll();
        callingHistories.sort(Comparator.comparing(CallingHistory::getCreatedDate));
        callingHistories.forEach(c -> {
            cHResultList.add(new CallingHistoryDTO(c));
        });

        return cHResultList;
    }

    @Override
    public List<CallingHistoryDTO> findByPhoneNumber(String phoneNumber) {
        LogUtil.logger.info("Find CallingHistories by phone number");
        return callingHistoryRepository.findCallingHistoriesByPhoneNumber(phoneNumber)
                .stream().map(CallingHistoryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<CallingHistoryDTO> findByUserId(Integer userId) {
        LogUtil.logger.info("Find CallingHistories by user id");
        return callingHistoryRepository.findCallingHistoriesByUserUserId(userId)
                .stream().map(CallingHistoryDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public CallingHistoryDTO create(CallingHistoryDTO callingHistoryDTO) {
        LogUtil.logger.info("Create new CallingHistory");
        try {
            /* Kiểm tra PhoneNumber đã có User chưa để cập nhật User cho lịch sử cuộc gọi */
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

        /* Cập nhật lại lịch sử cuộc gọi với User đã được tạo */
        List<CallingHistory> callingHistories = callingHistoryRepository.findCallingHistoriesByPhoneNumber(phoneNumber);
        for(CallingHistory c : callingHistories) {
            c.setUser(user);
        }
        callingHistoryRepository.saveAll(callingHistories);
    }
}
