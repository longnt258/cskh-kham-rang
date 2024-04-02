package com.api.cskh.springbootapi.dto;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.domain.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {
    private String title;
    private String description;
    private String bookDatetime;
    private String dentistName;

    public ScheduleDTO(Schedule schedule) {
        title = schedule.getTitle();
        description = schedule.getDescription();
        bookDatetime = DateUtil.convertDate2String(schedule.getBookingDatetime());
        dentistName = schedule.getDentist() != null ? schedule.getDentist().getFullName() : "";
    }
}
