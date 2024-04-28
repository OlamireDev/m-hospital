package com.olamiredev.mhospital.controller;

import com.olamiredev.mhospital.exceptionhandling.exception.StaffNotFoundException;
import com.olamiredev.mhospital.payload.StaffDTO;
import com.olamiredev.mhospital.payload.staff.update.request.StaffProfileUpdateRequestDTO;
import com.olamiredev.mhospital.service.StaffManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/staff")
public class StaffManagementController {

    @Autowired
    StaffManagementService staffManagementService;

    @PutMapping("/update-profile")
    public ResponseEntity<StaffDTO> updateStaffProfile(@Valid @RequestBody StaffProfileUpdateRequestDTO requestDTO) throws StaffNotFoundException {
        return ResponseEntity.ok(staffManagementService.updateStaffProfile(requestDTO));
    }

}
