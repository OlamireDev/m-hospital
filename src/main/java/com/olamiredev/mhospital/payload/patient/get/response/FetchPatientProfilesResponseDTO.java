package com.olamiredev.mhospital.payload.patient.get.response;

import java.util.List;

import com.olamiredev.mhospital.payload.PaginatedResponseDTO;
import com.olamiredev.mhospital.payload.PatientDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FetchPatientProfilesResponseDTO extends PaginatedResponseDTO {

    List<PatientDTO> patients;

    public FetchPatientProfilesResponseDTO(List<PatientDTO> patients, Integer pageNumber, Integer pageSize, Integer totalPages, Long totalElements) {
        super(pageNumber, pageSize, totalPages, totalElements);
        this.patients = patients;
    }

}
