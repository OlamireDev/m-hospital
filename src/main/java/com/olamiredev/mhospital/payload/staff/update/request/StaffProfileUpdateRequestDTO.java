package com.olamiredev.mhospital.payload.staff.update.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StaffProfileUpdateRequestDTO {

    @Length(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

}
