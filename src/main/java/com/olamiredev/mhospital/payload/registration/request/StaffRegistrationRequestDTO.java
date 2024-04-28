package com.olamiredev.mhospital.payload.registration.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StaffRegistrationRequestDTO {

    @JsonProperty("hospital_id")
    @Min(value = 1, message = "Hospital id must be greater than 0")
    private Long hospitalId;

    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

}
