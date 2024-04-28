package com.olamiredev.mhospital.repository;

import com.olamiredev.mhospital.model.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

    Optional<StaffEntity> findByUuid(String uuid);

}
