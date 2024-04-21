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
@Table(name = "t_calling_history")
public class CallingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "calling_history_id")
    private Integer callingHistoryId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private Boolean status; // true: complete, false: missing

    @Column(name = "description")
    private String description;

    @Column(name = "start_datetime")
    private Date startDatetime;

    @Column(name = "end_datetime")
    private Date endDatetime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id", referencedColumnName = "record_id")
    private Record record;
}
