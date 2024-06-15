package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
//@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "booking_datetime")
    private Date bookingDatetime;

    // default 1 = pending, 2 = success, 0 = cancel
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "notification", nullable = false)
    private boolean notif = false;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

    public Schedule(String title, String description, Date bookingDatetime, User user, Dentist dentist) {
        this.code = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.bookingDatetime = bookingDatetime;
        this.status = 1;
        this.user = user;
        this.dentist = dentist;
    }
}
