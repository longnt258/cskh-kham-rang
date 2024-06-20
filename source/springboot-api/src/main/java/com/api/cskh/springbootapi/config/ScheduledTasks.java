package com.api.cskh.springbootapi.config;

import com.api.cskh.springbootapi.common.utils.LogUtil;
import com.api.cskh.springbootapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final NotificationService notificationService;

    @Scheduled(fixedRate = 5000) // 60s 1 lần
    public void notificationTask() {
        LogUtil.logger.info("Chạy thông báo check lịch hẹn...");
        notificationService.runAutoNotification();
    }
}
