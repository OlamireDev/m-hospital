package com.olamiredev.mhospital.payload.patient.delete.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record DeletePatientsRequestDTO(@JsonProperty("start_date") LocalDateTime startDate, @JsonProperty("end_date") LocalDateTime endDate) {

    public boolean isValidRequest(){
        return  endDate.isAfter(startDate);
    }

}
