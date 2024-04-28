package com.olamiredev.mhospital.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "staff")
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    @ManyToOne
    @JoinColumn(nullable = false)
    private HospitalEntity hospital;

    @CreationTimestamp
    private LocalDateTime registrationDate;

}
