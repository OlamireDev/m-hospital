package com.olamiredev.mhospital.repository;

import com.olamiredev.mhospital.model.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {
}
