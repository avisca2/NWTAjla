package com.medical.reviewservice.services;

import com.medical.reviewservice.models.dto.TreatmentDTO;

import java.util.List;

public interface TreatmentService {
    TreatmentDTO save(TreatmentDTO treatmentDTO);
    List<TreatmentDTO> findAll();
    List<TreatmentDTO> findByPatientId(Integer patientId);
}
