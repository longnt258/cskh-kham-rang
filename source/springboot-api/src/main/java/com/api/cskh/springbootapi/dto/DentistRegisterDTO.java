package com.api.cskh.springbootapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DentistRegisterDTO {
    private String fullName;
    private String startDatetime;
    private String endDatetime;
    private int status;
}