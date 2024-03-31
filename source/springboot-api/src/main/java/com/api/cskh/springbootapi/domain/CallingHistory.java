package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_calling_history")
public class CallingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "calling_history_id")
    private Integer callingHistoryId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "description")
    private String description;

    @Column(name = "start_datetime")
    private Timestamp startDatetime;

    @Column(name = "end_datetime")
    private Timestamp endDatetime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id", referencedColumnName = "record_id")
    private Record record;
}
