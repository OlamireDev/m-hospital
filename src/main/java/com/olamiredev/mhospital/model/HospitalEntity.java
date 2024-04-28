package com.olamiredev.mhospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "hospitals")
public class HospitalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hospital_name")
    private String hospitalName;

    @OneToMany(mappedBy = "hospital")
    private List<StaffEntity> hospitalStaff;

    @OneToMany(mappedBy = "hospital")
    private List<PatientEntity> hospitalPatients;

}
