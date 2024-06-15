package com.api.cskh.springbootapi.controller;

import com.api.cskh.springbootapi.domain.Notification;
import com.api.cskh.springbootapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> findAll() {
        notificationService.runAutoNotification();
        return ResponseEntity.ok(notificationService.findAll());
    }
}
