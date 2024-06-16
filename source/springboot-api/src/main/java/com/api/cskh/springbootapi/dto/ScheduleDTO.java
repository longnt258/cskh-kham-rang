package com.api.cskh.springbootapi.dto;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.domain.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {
    private String code;
    private String title;
    private String description;
    private String bookingDatetime;
    private Integer status;
    private String dentistName;
    private String userFullName;
    private Integer userId;
    private Integer dentistId;
    private String phoneNumber;

    /* Phân loại cho user được quyền xem */
    // watcher: 0 --> admin, 1 --> user, 2 --> dentist
    public ScheduleDTO(Schedule schedule, int watcher) {
        code = schedule.getCode();
        title = schedule.getTitle();
        description = schedule.getDescription();
        status = schedule.getStatus();
        bookingDatetime = DateUtil.convertDate2String(schedule.getBookingDatetime());
        if (watcher == 0) {
            dentistName = schedule.getDentist() != null ? schedule.getDentist().getFullName() : "";
            userFullName = schedule.getUser() != null ? schedule.getUser().getFullName() : "";
            phoneNumber = schedule.getUser().getPhoneNumber();
        } else if (watcher == 1) {
            dentistName = schedule.getDentist() != null ? schedule.getDentist().getFullName() : "";
        } else if (watcher == 2) {
            userFullName = schedule.getUser() != null ? schedule.getUser().getFullName() : "";
        }
    }

    public ScheduleDTO(Schedule schedule) {
        code = schedule.getCode();
        title = schedule.getTitle();
        description = schedule.getDescription();
        status = schedule.getStatus();
        bookingDatetime = DateUtil.convertDate2String(schedule.getBookingDatetime());
        dentistName = schedule.getDentist() != null ? schedule.getDentist().getFullName() : "";
        userFullName = schedule.getUser() != null ? schedule.getUser().getFullName() : "";
        dentistId = schedule.getDentist().getDentistId();
        userId = schedule.getUser().getUserId();
        phoneNumber = schedule.getUser().getPhoneNumber();
    }

}
