package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.dto.ResponseDTO;
import com.api.cskh.springbootapi.dto.ScheduleDTO;
import com.api.cskh.springbootapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ScheduleDTO>>> findAll() {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAll(), "OK", 1));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseDTO<ScheduleDTO>> findbyCode(@PathVariable String code) {
        ResponseDTO<ScheduleDTO> responseDTO;
        ScheduleDTO scheduleDTO = scheduleService.findByCode(code);
        if(scheduleDTO != null) {
            responseDTO = new ResponseDTO<>(scheduleDTO, "OK", 1);
        } else {
            responseDTO = new ResponseDTO<>(null, "This code is not found!", 0);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseDTO<List<ScheduleDTO>>> findUserSchedule(@RequestParam int userId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findByUserId(userId), "OK", 1));
    }

    @GetMapping("/dentist")
    public ResponseEntity<ResponseDTO<List<ScheduleDTO>>> findDentistSchedule(@RequestParam int dentistId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findByDentistId(dentistId), "OK", 1));
    }
}
