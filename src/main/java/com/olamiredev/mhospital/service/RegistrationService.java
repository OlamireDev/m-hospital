package com.olamiredev.mhospital.service;

import com.olamiredev.mhospital.exceptionhandling.exception.HospitalNotFoundException;
import com.olamiredev.mhospital.model.StaffEntity;
import com.olamiredev.mhospital.payload.StaffDTO;
import com.olamiredev.mhospital.payload.registration.request.StaffRegistrationRequestDTO;
import com.olamiredev.mhospital.repository.HospitalRepository;
import com.olamiredev.mhospital.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    StaffRepository staffRepository;

    public StaffDTO registerStaff(StaffRegistrationRequestDTO requestDTO) throws HospitalNotFoundException {
        var hospital = hospitalRepository.findById(requestDTO.getHospitalId())
                .orElseThrow(() -> new HospitalNotFoundException(requestDTO.getHospitalId()));
        var newUUID = UUID.randomUUID().toString();
        while(staffRepository.findByUuid(newUUID).isPresent()){
            newUUID = UUID.randomUUID().toString();
        }
        var staffEntity = StaffEntity.builder().name(requestDTO.getName())
                .hospital(hospital).uuid(newUUID).build();
        staffRepository.save(staffEntity);
        return StaffDTO.fromEntity(staffEntity);
    }

}
