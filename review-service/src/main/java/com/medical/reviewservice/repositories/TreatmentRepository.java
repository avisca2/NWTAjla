package com.medical.reviewservice.repositories;

import com.medical.reviewservice.models.entities.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByPatientId(Integer patientId);
}
