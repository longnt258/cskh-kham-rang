package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
//@Builder
@Entity
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

    @Column(name = "status", nullable = false)
    private Integer status;

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
