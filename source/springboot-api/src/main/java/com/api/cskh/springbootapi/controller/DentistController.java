package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.common.Constants;
import com.api.cskh.springbootapi.common.utils.DateUtil;
import com.api.cskh.springbootapi.dto.DentistDTO;
import com.api.cskh.springbootapi.dto.ResponseDTO;
import com.api.cskh.springbootapi.dto.ScheduleDTO;
import com.api.cskh.springbootapi.service.DentistService;
import com.api.cskh.springbootapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/dentist")
@RequiredArgsConstructor
public class DentistController {
    private final DentistService dentistService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<DentistDTO>>> findAll(){
        return ResponseEntity.ok(new ResponseDTO<>(dentistService.findAll(),"OK", 1));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<DentistDTO>> createByAdmin(@RequestBody DentistDTO dentistDTO) {
        ResponseDTO<DentistDTO> response;
        DentistDTO dentistDTOResponse = null;
        try {
            String message = Constants.OK;
            int status = 1;
            if(dentistDTO.getFullName() == null || Objects.equals(dentistDTO.getFullName(), "")) {
                message = Constants.IS_EMPTY("Full name");
                status = 0;
            } else if(dentistDTO.getStartDateTimeString() == null || Objects.equals(dentistDTO.getStartDateTimeString(), "")) {
                message = Constants.IS_EMPTY("Start datetime");
                status = 0;
            }
            // no booking datetime checked
            if(status == 1) {
                dentistDTO.setStartDateTime(DateUtil.convertString2Date(dentistDTO.getStartDateTimeString()));
                if(dentistDTO.getEndDateTimeString() != null && !Objects.equals(dentistDTO.getEndDateTimeString(), "")) {
                    dentistDTO.setEndDateTime(DateUtil.convertString2Date(dentistDTO.getEndDateTimeString()));
                }

                dentistDTOResponse = dentistService.createByAdmin(dentistDTO);
                if(dentistDTOResponse == null) {
                    message = Constants.FAILURE_REGISTER;
                    status = 0;
                }
            }
            response = new ResponseDTO<>(dentistDTOResponse, message, status);
        } catch (Exception e) {
            response = new ResponseDTO<>(null, Constants.REGISTER_ERROR, 0);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO<DentistDTO>> update(@RequestParam Integer dentistId, @RequestBody DentistDTO dentistDTO) {
        ResponseDTO<DentistDTO> response;
        String message = Constants.OK;
        int status = 1;
        if(dentistService.findById(dentistId) == null) {
            message = Constants.NOT_FOUND("Dentist id");
            status = 0;
        } else if(dentistService.update(dentistId, dentistDTO) == null) {
            message = Constants.FAILURE_UPDATE;
            status = 0;
        }
        if(status == 1) {
            response = new ResponseDTO<>(dentistService.findById(dentistId), message, status);
        } else {
            response = new ResponseDTO<>(null, message, status);
        }
        return ResponseEntity.ok(response);
    }
}
