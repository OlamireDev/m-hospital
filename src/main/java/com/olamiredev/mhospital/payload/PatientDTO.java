package com.olamiredev.mhospital.payload;

import java.time.LocalDateTime;

import com.olamiredev.mhospital.model.PatientEntity;

public record PatientDTO(Long id, String name, Integer age, LocalDateTime lastVisitDate) {

    public static PatientDTO fromEntity(PatientEntity patientEntity) {
        return new PatientDTO(patientEntity.getId(), patientEntity.getName(), patientEntity.getAge(), patientEntity.getLastVisitDate());
    }

}
