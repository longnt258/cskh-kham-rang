package com.api.cskh.springbootapi.repository;

import com.api.cskh.springbootapi.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
