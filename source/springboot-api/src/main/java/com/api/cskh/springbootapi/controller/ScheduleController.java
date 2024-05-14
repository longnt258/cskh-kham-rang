package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.common.Constants;
import com.api.cskh.springbootapi.dto.ResponseDTO;
import com.api.cskh.springbootapi.dto.ScheduleDTO;
import com.api.cskh.springbootapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ScheduleDTO>>> findAll() {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAll(), Constants.OK, 1));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ResponseDTO<ScheduleDTO>> findbyCode(@PathVariable String code) {
        ResponseDTO<ScheduleDTO> responseDTO;
        ScheduleDTO scheduleDTO = scheduleService.findByCode(code);
        if(scheduleDTO != null) {
            responseDTO = new ResponseDTO<>(scheduleDTO, Constants.OK, 1);
        } else {
            responseDTO = new ResponseDTO<>(null, Constants.NOT_FOUND("Code"), 0);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<ResponseDTO<List<ScheduleDTO>>> findUserSchedule(@RequestParam int userId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findByUserId(userId), Constants.OK, 1));
    }

    @GetMapping("/dentist")
    public ResponseEntity<ResponseDTO<List<ScheduleDTO>>> findDentistSchedule(@RequestParam int dentistId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findByDentistId(dentistId), Constants.OK, 1));
    }

    @PostMapping("/create-by-admin")
    public ResponseEntity<ResponseDTO<ScheduleDTO>> createByAdmin(@RequestBody ScheduleDTO scheduleDTO) {
        ResponseDTO<ScheduleDTO> response;
        ScheduleDTO scheduleDTOResponse = null;
        try {
            String message = Constants.OK;
            int status = 1;
            if(scheduleDTO.getTitle() == null || Objects.equals(scheduleDTO.getTitle(), "")) {
                message = Constants.IS_EMPTY("Title");
                status = 0;
            } else if(scheduleDTO.getBookingDatetime() == null || Objects.equals(scheduleDTO.getBookingDatetime(), "")) {
                message = Constants.IS_EMPTY("Booking datetime");
                status = 0;
            }
            // no booking datetime checked
            if(status == 1) {
                scheduleDTOResponse = scheduleService.createByAdmin(scheduleDTO);
                if(scheduleDTOResponse == null) {
                    message = Constants.FAILURE_REGISTER;
                    status = 0;
                }
            }
            response = new ResponseDTO<>(scheduleDTOResponse, message, status);
        } catch (Exception e) {
            response = new ResponseDTO<>(null, Constants.REGISTER_ERROR, 0);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-by-user")
    public ResponseEntity<ResponseDTO<ScheduleDTO>> createByUser(@RequestBody ScheduleDTO scheduleDTO) {
        ResponseDTO<ScheduleDTO> response;
        ScheduleDTO scheduleDTOResponse = null;
        try {
            String message = Constants.OK;
            int status = 1;
            if(scheduleDTO.getTitle() == null || Objects.equals(scheduleDTO.getTitle(), "")) {
                message = Constants.IS_EMPTY("Title");
                status = 0;
            } else if(scheduleDTO.getBookingDatetime() == null || Objects.equals(scheduleDTO.getBookingDatetime(), "")) {
                message = Constants.IS_EMPTY("Booking datetime");
                status = 0;
            }
            // no booking datetime checked
            if(status == 1) {
                scheduleDTOResponse = scheduleService.bookingByUser(scheduleDTO);
                if(scheduleDTOResponse == null) {
                    message = Constants.FAILURE_REGISTER;
                    status = 0;
                }
            }
            response = new ResponseDTO<>(scheduleDTOResponse, message, status);
        } catch (Exception e) {
            response = new ResponseDTO<>(null, Constants.REGISTER_ERROR, 0);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO<ScheduleDTO>> update(@RequestParam String scheduleCode, @RequestBody ScheduleDTO scheduleDTO) {
        ResponseDTO<ScheduleDTO> response;
        String message = Constants.OK;
        int status = 1;
        if(scheduleService.findByCode(scheduleCode) == null) {
            message = Constants.NOT_FOUND("Schedule code");
            status = 0;
        } else if(scheduleService.update(scheduleCode, scheduleDTO) == null) {
            message = Constants.FAILURE_UPDATE;
            status = 0;
        }
        if(status == 1) {
            response = new ResponseDTO<>(scheduleService.findByCode(scheduleCode), message, status);
        } else {
            response = new ResponseDTO<>(null, message, status);
        }
        return ResponseEntity.ok(response);
    }
}
