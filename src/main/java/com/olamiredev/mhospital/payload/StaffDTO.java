package com.olamiredev.mhospital.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olamiredev.mhospital.model.StaffEntity;

import java.time.LocalDateTime;

public record StaffDTO(Long id, String name, String uuid, @JsonProperty("registration_date") LocalDateTime registrationDate) {

    public static StaffDTO fromEntity(StaffEntity staffEntity) {
        return new StaffDTO(staffEntity.getId(), staffEntity.getName(), staffEntity.getUuid(), staffEntity.getRegistrationDate());
    }

}
