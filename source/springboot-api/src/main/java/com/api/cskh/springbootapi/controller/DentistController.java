package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.dto.DentistDTO;
import com.api.cskh.springbootapi.dto.ResponseDTO;
import com.api.cskh.springbootapi.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final DentistService dentistService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<DentistDTO>>> findAll(){
        return ResponseEntity.ok(new ResponseDTO<>(dentistService.findAll(),"OK", 1));
    }

}
