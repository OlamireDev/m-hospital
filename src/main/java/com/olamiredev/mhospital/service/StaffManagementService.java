package com.olamiredev.mhospital.service;

import com.olamiredev.mhospital.exceptionhandling.exception.StaffNotFoundException;
import com.olamiredev.mhospital.payload.StaffDTO;
import com.olamiredev.mhospital.payload.staff.update.request.StaffProfileUpdateRequestDTO;
import com.olamiredev.mhospital.repository.StaffRepository;
import com.olamiredev.mhospital.utils.StaffContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffManagementService {

    @Autowired
    StaffRepository staffRepository;

    public StaffDTO updateStaffProfile(StaffProfileUpdateRequestDTO requestDTO) throws StaffNotFoundException {
        var staffUUID = StaffContextHelper.getStaffUuid();
        var staffEntity = staffRepository.findByUuid(staffUUID).orElseThrow(() -> new StaffNotFoundException(staffUUID));
        staffEntity.setName(requestDTO.getName());
        staffRepository.save(staffEntity);
        return StaffDTO.fromEntity(staffEntity);
    }

}
