package com.olamiredev.mhospital.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.stream.Collectors;

import com.olamiredev.mhospital.exceptionhandling.exception.PatientNotFoundException;
import com.olamiredev.mhospital.exceptionhandling.exception.RestrictedAccessException;
import com.olamiredev.mhospital.payload.patient.delete.request.DeletePatientsRequestDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.olamiredev.mhospital.exceptionhandling.exception.StaffNotFoundException;
import com.olamiredev.mhospital.payload.PatientDTO;
import com.olamiredev.mhospital.payload.patient.get.request.FetchPatientProfilesRequestDTO;
import com.olamiredev.mhospital.payload.patient.get.response.FetchPatientProfilesResponseDTO;
import com.olamiredev.mhospital.repository.PatientRepository;
import com.olamiredev.mhospital.repository.StaffRepository;
import com.olamiredev.mhospital.utils.StaffContextHelper;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    FileCleanUpService fileCleanUpService;

    public FetchPatientProfilesResponseDTO fetchPatientProfiles(FetchPatientProfilesRequestDTO requestDTO) throws StaffNotFoundException {
        var staffUUID = StaffContextHelper.getStaffUuid();
        var staffEntity = staffRepository.findByUuid(staffUUID).orElseThrow(() -> new StaffNotFoundException(staffUUID));
        var page = PageRequest.of(requestDTO.getPageNumber(), requestDTO.getPageSize());
        var patientsPage = patientRepository.findAllByHospitalAndAge(staffEntity.getHospital(), page);
        var patients = patientsPage.getContent().stream().map(PatientDTO::fromEntity).collect(Collectors.toList());
        return new FetchPatientProfilesResponseDTO(patients, patientsPage.getNumber(), patientsPage.getSize(), patientsPage.getTotalPages(), patientsPage.getTotalElements());
    }

    public FileSystemResource fetchPatientInfoAsCSV(Long patientId) throws StaffNotFoundException, IOException, PatientNotFoundException, RestrictedAccessException {
        var staffUUID = StaffContextHelper.getStaffUuid();
        var staffEntity = staffRepository.findByUuid(staffUUID).orElseThrow(() -> new StaffNotFoundException(staffUUID));
        var patientEntity = patientRepository.findByIdAndIsDeletedFalse(patientId).orElseThrow(() -> new PatientNotFoundException(patientId));
        if(!Objects.equals(staffEntity.getHospital().getId(), patientEntity.getHospital().getId())) {
            throw new RestrictedAccessException(staffUUID, patientId);
        }
        var csvHeaderResource = new ClassPathResource("static/patient_profile_header.txt");
        var headerContent = csvHeaderResource.getContentAsString(Charset.defaultCharset());
        String patientProfileCSVsB = headerContent + "\n" + patientEntity.getId() + ", " +
                patientEntity.getName() + ", " + patientEntity.getAge() + ", " +
                patientEntity.getLastVisitDate();
        var patientCSVResourcePath = Paths.get(resourceLoader.getResource("classpath:").getURI()).resolve("static").resolve("patient_" + patientId +".csv");
        if(!patientCSVResourcePath.toFile().exists()) {
            Files.createFile(patientCSVResourcePath);
        }
        Files.writeString(patientCSVResourcePath, patientProfileCSVsB, StandardOpenOption.WRITE);
        var fileResource = new FileSystemResource(patientCSVResourcePath.toFile());
        fileCleanUpService.updateGeneratedFile(fileResource);
        return fileResource;
    }

    public Integer deletePatients(DeletePatientsRequestDTO requestDTO) throws BadRequestException, StaffNotFoundException {
        var staffUUID = StaffContextHelper.getStaffUuid();
        var staffEntity = staffRepository.findByUuid(staffUUID).orElseThrow(() -> new StaffNotFoundException(staffUUID));
        if(!requestDTO.isValidRequest()) {
            throw new BadRequestException("Date range is not valid");
        }
        var patientThatWillBeDeleted = patientRepository.findAllByHospitalAndDateRange(staffEntity.getHospital(), requestDTO.startDate(), requestDTO.endDate());
        patientThatWillBeDeleted.forEach(patientEntity -> {
            patientEntity.setIsDeleted(true);
        });
        patientRepository.saveAll(patientThatWillBeDeleted);
        return patientThatWillBeDeleted.size();
    }

}
