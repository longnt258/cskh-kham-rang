package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_dentist")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dentist_id")
    private Integer dentistId;

    @Column(name = "full_name")
    private String fullName;

    // is active = true, no active = false
    @Column(name = "status")
    private Boolean status;

    @Column(name = "start_working_datetime")
    private Date startDatetime;

    @Column(name = "end_working_datetime")
    private Date endDatetime;

    @OneToMany(mappedBy = "dentist")
    private List<Schedule> schedules;
}
