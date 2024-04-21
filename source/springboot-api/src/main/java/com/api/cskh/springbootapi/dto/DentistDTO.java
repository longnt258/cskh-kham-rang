package com.api.cskh.springbootapi.dto;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.domain.Dentist;
import lombok.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DentistDTO {
    private int dentistId;
    private String fullName;
    private Boolean status;
    private Date endDateTime;
    private Date startDateTime;
    private String startDateTimeString;
    private String endDateTimeString;
    private List<ScheduleDTO> schedules = new ArrayList<>();

    /* Các input khi create hoặc update: fullName, status, startDateTimeString, endDateTimeString */

    public DentistDTO(Dentist dentist) {
        this.dentistId = dentist.getDentistId();
        this.fullName = dentist.getFullName();
        this.startDateTime = dentist.getStartDatetime();
        this.endDateTime = dentist.getEndDatetime();
        this.status = dentist.getStatus();
        dentist.getSchedules().forEach(s -> {
            schedules.add(new ScheduleDTO(s, 2));
        });
    }
}
