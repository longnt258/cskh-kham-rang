package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    private Date createdDate;

    @OneToMany(mappedBy = "dentist")
    private List<Schedule> schedules;
}
