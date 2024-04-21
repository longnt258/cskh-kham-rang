package com.api.cskh.springbootapi.dto;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.domain.CallingHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallingHistoryDTO {
    private String phoneNumber;
    private boolean status; // true: complete, false: missing
    private String description;
    private String startDate;
    private String endDate;
    private String userFullName;

    public CallingHistoryDTO(CallingHistory callingHistory) {
        phoneNumber = callingHistory.getPhoneNumber();
        status = callingHistory.getStatus();
        description = callingHistory.getDescription();
        startDate = DateUtil.convertDate2String(callingHistory.getStartDatetime());
        endDate = callingHistory.getEndDatetime() != null ? DateUtil.convertDate2String(callingHistory.getEndDatetime()) : null;
        userFullName = callingHistory.getUser() != null ? callingHistory.getUser().getFullName() : "Unknown";
    }
}
