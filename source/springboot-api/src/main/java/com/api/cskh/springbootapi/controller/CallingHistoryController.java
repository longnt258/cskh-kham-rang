package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.common.Constants;
import com.api.cskh.springbootapi.dto.CallingHistoryDTO;
import com.api.cskh.springbootapi.dto.ResponseDTO;
import com.api.cskh.springbootapi.service.CallingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calling-history")
@RequiredArgsConstructor
public class CallingHistoryController {
    private final CallingHistoryService callingHistoryService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<CallingHistoryDTO>>> findAll(){
        return ResponseEntity.ok(new ResponseDTO<>(callingHistoryService.findAll(),"OK", 1));
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<ResponseDTO<List<CallingHistoryDTO>>> findByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(new ResponseDTO<>(callingHistoryService.findByUserId(userId),"OK", 1));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<CallingHistoryDTO>> create(@RequestBody CallingHistoryDTO callingHistoryDTO) {
        ResponseDTO<CallingHistoryDTO> response;
        CallingHistoryDTO callingHistoryDTOResponse;
        try {
            String message = Constants.OK;
            int status = 1;
            callingHistoryDTOResponse = callingHistoryService.create(callingHistoryDTO);
            response = new ResponseDTO<>(callingHistoryDTOResponse, message, status);
        } catch (Exception e) {
            response = new ResponseDTO<>(null, Constants.REGISTER_ERROR, 0);
        }
        return ResponseEntity.ok(response);
    }
}
