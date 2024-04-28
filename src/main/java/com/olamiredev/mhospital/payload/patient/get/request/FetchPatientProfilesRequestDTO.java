package com.olamiredev.mhospital.payload.patient.get.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FetchPatientProfilesRequestDTO {

    @JsonProperty("page_number")
    @Min(value = 0, message = "Page number must be greater than or equal to 0")
    private Integer pageNumber;

    @JsonProperty("page_size")
    @Min(value = 1, message = "Page size must be greater than 0")
    private Integer pageSize;

}
