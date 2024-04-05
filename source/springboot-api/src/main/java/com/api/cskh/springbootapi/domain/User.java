package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", unique = true, length = 10)
    private String phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<CallingHistory> callingHistories;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Schedule> schedules;
}
