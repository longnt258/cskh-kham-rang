package com.api.cskh.springbootapi.dto;

import com.api.cskh.springbootapi.domain.Schedule;
import com.api.cskh.springbootapi.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private List<ScheduleDTO> schedules = new LinkedList<>();

    public UserDTO(User user) {
        username = user.getUsername();
        password = user.getPassword();
        fullName = user.getFullName();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        for(Schedule schedule : user.getSchedules()) {
            schedules.add(new ScheduleDTO(schedule));
        }
    }
}
