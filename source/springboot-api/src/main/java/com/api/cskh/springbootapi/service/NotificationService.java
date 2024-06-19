package com.api.cskh.springbootapi.service;

import com.api.cskh.springbootapi.domain.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> findAll();

    void runAutoNotification();

    Notification updateStatus(Integer notificationId);
}
