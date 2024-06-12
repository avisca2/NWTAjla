package com.medical.reviewservice.models.dto;

import com.medical.reviewservice.models.transients.Doctor;
import com.medical.reviewservice.models.transients.Pacijent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreatmentDTO {
    private Long id;
    private Integer pacijentID;
    private Integer doctorID;
    private String description;
    private Pacijent patient;
    private Doctor doctor;
}
