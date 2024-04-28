package com.olamiredev.mhospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private Boolean isDeleted = false;

    @Column(name ="last_visit_date")
    private LocalDateTime lastVisitDate;

    @ManyToOne
    private HospitalEntity hospital;

}
