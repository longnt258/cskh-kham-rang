package com.api.cskh.springbootapi.config;

import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.domain.*;
import com.api.cskh.springbootapi.dto.CallingHistoryDTO;
import com.api.cskh.springbootapi.repository.*;
import com.api.cskh.springbootapi.service.CallingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SeedDataRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final DentistRepository dentistRepository;
    private final ScheduleRepository scheduleRepository;
    private final CallingHistoryRepository callingHistoryRepository;
    private final NotificationRepository notificationRepository;

    // Initializing data
    @Override
    public void run(String... args) throws Exception {
//        // Create Users
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

//         Create Admin
        Admin admin = Admin.builder()
                .username("admin")
                .password("admin")
                .fullName("Admin")
                .email("admin@gmail.com")
                .build();
        adminRepository.save(admin);

//         Create dentists
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
        Schedule schedule1 = new Schedule("Schedule 1", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 10, 9, 0, 0), user2, dentist1);
        Schedule schedule2 = new Schedule("Schedule 2", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 15, 16, 0, 0), user3, dentist5);
        Schedule schedule3 = new Schedule("Schedule 3", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 20, 19, 0, 0), user1, dentist3);
        Schedule schedule4 = new Schedule("Schedule 4", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 3, 19, 4, 0), user1, dentist3);
        Schedule schedule5 = new Schedule("Schedule 4", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 3, 19, 4, 0), user1, dentist3);
        Schedule schedule6 = new Schedule("Schedule 4", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 3, 19, 4, 0), user1, dentist3);
        Schedule schedule7 = new Schedule("Schedule 4", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 3, 19, 4, 0), user1, dentist3);
        Schedule schedule8 = new Schedule("Schedule 4", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 3, 19, 4, 0), user1, dentist3);
        Schedule schedule9 = new Schedule("Schedule 4", "Bi sau rang", DateUtil.getTimeWithDate(2024, 4, 3, 19, 4, 0), user1, dentist3);
        Schedule schedule10 = new Schedule("Schedule 4", "Bi sau rang", DateUtil.getTimeWithDate(2024, 5, 3, 19, 4, 0), user1, dentist3);
        scheduleRepository.saveAll(List.of(schedule1, schedule2, schedule3,schedule4,schedule5,schedule6,schedule7,schedule8,schedule9,schedule10));

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

        CallingHistory callingHistory = new CallingHistory()
                .builder()
                .phoneNumber("0123456789")
                .status(true)
                .description("Cuoc goi hoan thanh")
                .startDatetime(new Date())
                .endDatetime(new Date())
                .user(user1)
                .build();
        callingHistoryRepository.save(callingHistory);
//        Schedule schedule1 = scheduleRepository.findScheduleByCode("Schedule 1");
//        System.out.println(schedule1.getCode());

        Notification notification = new Notification();
        notification.setContent("Test notif");
        notification.setStatus(false);
        notification.setCreatedDate(new Date());
        notificationRepository.save(notification);
    }
}
