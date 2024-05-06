package com.api.cskh.springbootapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "content")
    private String content;

    @OneToOne(mappedBy = "record")
    private CallingHistory callingHistory;
}
