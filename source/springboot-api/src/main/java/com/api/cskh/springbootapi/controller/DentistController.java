package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.domain.Dentist;
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
    public ResponseEntity<List<Dentist>> findAll(){
        return ResponseEntity.ok(dentistService.findAll());
    }

}
