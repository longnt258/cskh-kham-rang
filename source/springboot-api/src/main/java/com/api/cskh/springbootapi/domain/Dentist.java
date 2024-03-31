package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

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

    @Column(name = "status")
    private Boolean status;

    @Column(name = "start_working_datetime")
    private Timestamp startDatetime;

    @Column(name = "end_working_datetime")
    private Timestamp endDatetime;

    @OneToMany(mappedBy = "dentist")
    private Set<Schedule> schedules;
}
