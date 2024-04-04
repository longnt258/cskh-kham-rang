package com.api.cskh.springbootapi.config;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.domain.Admin;
import com.api.cskh.springbootapi.domain.Dentist;
import com.api.cskh.springbootapi.domain.Schedule;
import com.api.cskh.springbootapi.domain.User;
import com.api.cskh.springbootapi.repository.AdminRepository;
import com.api.cskh.springbootapi.repository.DentistRepository;
import com.api.cskh.springbootapi.repository.ScheduleRepository;
import com.api.cskh.springbootapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeedDataRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final DentistRepository dentistRepository;
    private final ScheduleRepository scheduleRepository;

    // Initializing data
    @Override
    public void run(String... args) throws Exception {
        // Create Users
        User user1 = User.builder()
                .username("user1")
                .password("123456")
                .fullName("Long")
                .email("user1@gmail.com")
                .phoneNumber("0123456789")
                .build();
        User user2 = User.builder()
                .username("user2")
                .password("123456")
                .fullName("user 2")
                .email("user2@gmail.com")
                .phoneNumber("0987654321")
                .build();
        User user3 = User.builder()
                .username("user3")
                .password("123456")
                .fullName("user 3")
                .email("user3@gmail.com")
                .phoneNumber("0159357123")
                .build();
        userRepository.saveAll(List.of(user1, user2, user3));

        // Create Admin
        Admin admin = Admin.builder()
                .username("admin")
                .password("admin")
                .fullName("Admin")
                .email("admin@gmail.com")
                .build();
        adminRepository.save(admin);

        // Create dentists
        Dentist dentist1 = Dentist.builder()
                .fullName("Dentist 1")
                .status(true)
                .startDatetime(DateUtil.getTimeWithoutDate(8, 0, 0, 0))
                .endDatetime(DateUtil.getTimeWithoutDate(17, 0, 0, 0))
                .build();
        Dentist dentist2 = Dentist.builder()
                .fullName("Dentist 2")
                .status(true)
                .startDatetime(DateUtil.getTimeWithoutDate(10, 30, 0, 0))
                .endDatetime(DateUtil.getTimeWithoutDate(19, 0, 0, 0))
                .build();
        Dentist dentist3 = Dentist.builder()
                .fullName("Dentist 3")
                .status(true)
                .startDatetime(DateUtil.getTimeWithoutDate(17, 30, 0, 0))
                .endDatetime(DateUtil.getTimeWithoutDate(23, 0, 0, 0))
                .build();
        Dentist dentist4 = Dentist.builder()
                .fullName("Dentist 4")
                .status(true)
                .startDatetime(DateUtil.getTimeWithoutDate(12, 30, 0, 0))
                .endDatetime(DateUtil.getTimeWithoutDate(18, 30, 0, 0))
                .build();
        Dentist dentist5 = Dentist.builder()
                .fullName("Dentist 5")
                .status(true)
                .startDatetime(DateUtil.getTimeWithoutDate(10, 0, 0, 0))
                .endDatetime(DateUtil.getTimeWithoutDate(16, 0, 0, 0))
                .build();
        dentistRepository.saveAll(List.of(dentist1, dentist2, dentist3, dentist4, dentist5));

        // Create Schedules
        Schedule schedule1 = Schedule.builder()
                .code("ABC")
                .title("Schedule 1")
                .description("Bi sau rang")
                .bookingDatetime(DateUtil.getTimeWithDate(2024, 4, 10, 9, 0, 0))
                .user(user2)
                .dentist(dentist1)
                .build();
        Schedule schedule2 = Schedule.builder()
                .code("DEF")
                .title("Schedule 2")
                .description("Bi sau rang")
                .bookingDatetime(DateUtil.getTimeWithDate(2024, 4, 15, 16, 0, 0))
                .user(user3)
                .dentist(dentist5)
                .build();
        Schedule schedule3 = Schedule.builder()
                .code("QWE")
                .title("Schedule 3")
                .description("Bi sau rang")
                .bookingDatetime(DateUtil.getTimeWithDate(2024, 4, 20, 19, 0, 0))
                .user(user1)
                .dentist(dentist3)
                .build();
        scheduleRepository.saveAll(List.of(schedule1, schedule2, schedule3));

        // Set Schedule for User
        List<Schedule> schedules1 = new ArrayList(List.of(schedule1)); // User 1
        user1.setSchedules(schedules1);

        List<Schedule> schedules2 = new ArrayList(List.of(schedule2)); // User 3
        user3.setSchedules(schedules2);

        List<Schedule> schedules3 = new ArrayList(List.of(schedule3)); // User 2
        user2.setSchedules(schedules3);
        userRepository.saveAll(List.of(user1, user2, user3));

        // Set Schedule for Dentist
        dentist1.setSchedules(schedules1);
        dentist3.setSchedules(schedules3);
        dentist5.setSchedules(schedules2);
        dentistRepository.saveAll(List.of(dentist1, dentist3, dentist5));
    }
}
