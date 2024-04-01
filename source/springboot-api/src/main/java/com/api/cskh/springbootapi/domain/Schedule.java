package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "booking_datetime")
    private Date bookingDatetime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;
}
