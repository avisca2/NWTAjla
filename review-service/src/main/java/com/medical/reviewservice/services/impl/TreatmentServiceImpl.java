package com.medical.reviewservice.services.impl;

import com.medical.reviewservice.exceptions.ResourceNotFoundException;
import com.medical.reviewservice.models.dto.TreatmentDTO;
import com.medical.reviewservice.models.entities.Treatment;
import com.medical.reviewservice.repositories.TreatmentRepository;
import com.medical.reviewservice.services.TreatmentService;
import com.medical.reviewservice.services.client.DoctorClientService;
import com.medical.reviewservice.services.client.PatientClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final ModelMapper modelMapper;
    private final PatientClientService patientClientService;
    private final DoctorClientService doctorClientService;

    @Override
    public TreatmentDTO save(TreatmentDTO treatmentDTO) {
        Treatment treatment = modelMapper.map(treatmentDTO, Treatment.class);
        treatment.setDoctor(doctorClientService.getDoctorById(treatmentDTO.getDoctorID()));
        treatment.setPacijent(patientClientService.getPatientById(treatmentDTO.getPacijentID()));
        treatment.setPatientId(treatmentDTO.getPacijentID());
        Treatment savedTreatment = treatmentRepository.save(treatment);
        return modelMapper.map(savedTreatment, TreatmentDTO.class);
    }

    @Override
    public List<TreatmentDTO> findAll() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatments.stream().map(treatment -> modelMapper.map(treatment, TreatmentDTO.class)).toList();
    }

    @Override
    public List<TreatmentDTO> findByPatientId(Integer patientId) {
        patientClientService.getPatientById(patientId);

        List<Treatment> treatments = treatmentRepository.findByPatientId(patientId);
        if (treatments.isEmpty()) {
            throw new ResourceNotFoundException("No treatments found for patient with ID: " + patientId);
        }
        return treatments.stream().map(treatment -> {
            treatment.setDoctor(doctorClientService.getDoctorById(treatment.getDoctorId()));
            treatment.setPacijent(patientClientService.getPatientById(patientId));
            return modelMapper.map(treatment, TreatmentDTO.class);
        }).collect(Collectors.toList());
    }
}
