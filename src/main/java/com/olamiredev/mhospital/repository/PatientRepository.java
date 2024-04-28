package com.olamiredev.mhospital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olamiredev.mhospital.model.HospitalEntity;
import com.olamiredev.mhospital.model.PatientEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    @Query("SELECT pe FROM PatientEntity pe WHERE pe.hospital = :hospital AND  pe.age >= 2 AND pe.isDeleted = false")
    Page<PatientEntity> findAllByHospitalAndAge(HospitalEntity hospital, Pageable pageable);

    Optional<PatientEntity> findByIdAndIsDeletedFalse(Long id);

    @Query("SELECT pe FROM PatientEntity pe WHERE pe.hospital = :hospital AND pe.lastVisitDate >= :startDate AND pe.lastVisitDate <= :endDate AND pe.isDeleted = false")
    List<PatientEntity> findAllByHospitalAndDateRange(HospitalEntity hospital, LocalDateTime startDate, LocalDateTime endDate);
    
}
