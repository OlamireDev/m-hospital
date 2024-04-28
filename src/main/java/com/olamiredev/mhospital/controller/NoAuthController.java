package com.olamiredev.mhospital.controller;

import com.olamiredev.mhospital.exceptionhandling.exception.HospitalNotFoundException;
import com.olamiredev.mhospital.payload.StaffDTO;
import com.olamiredev.mhospital.payload.registration.request.StaffRegistrationRequestDTO;
import com.olamiredev.mhospital.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class NoAuthController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register/staff")
    public ResponseEntity<StaffDTO> registerStaff(@Valid @RequestBody StaffRegistrationRequestDTO requestDTO) throws HospitalNotFoundException {
        return ResponseEntity.ok(registrationService.registerStaff(requestDTO));
    }

}
