package com.olamiredev.mhospital.controller;

import com.olamiredev.mhospital.exceptionhandling.exception.PatientNotFoundException;
import com.olamiredev.mhospital.exceptionhandling.exception.RestrictedAccessException;
import com.olamiredev.mhospital.exceptionhandling.exception.StaffNotFoundException;
import com.olamiredev.mhospital.payload.patient.delete.request.DeletePatientsRequestDTO;
import com.olamiredev.mhospital.payload.patient.get.request.FetchPatientProfilesRequestDTO;
import com.olamiredev.mhospital.payload.patient.get.response.FetchPatientProfilesResponseDTO;
import com.olamiredev.mhospital.service.PatientService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/fetch")
    public ResponseEntity<FetchPatientProfilesResponseDTO> fetchPatients(@Valid @RequestBody FetchPatientProfilesRequestDTO requestDTO) throws StaffNotFoundException {
        return ResponseEntity.ok(patientService.fetchPatientProfiles(requestDTO));
    }

    @GetMapping("/csv/{patient_id}")
    public ResponseEntity<FileSystemResource> fetchPatientCSV(@PathVariable Long patient_id, HttpServletResponse response) throws StaffNotFoundException, RestrictedAccessException, IOException, PatientNotFoundException {
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;");
        return ResponseEntity.ok(patientService.fetchPatientInfoAsCSV(patient_id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deletePatientsForDateRange(@Valid @RequestBody DeletePatientsRequestDTO requestDTO) throws BadRequestException, StaffNotFoundException {
        return ResponseEntity.ok(patientService.deletePatients(requestDTO));
    }

}
