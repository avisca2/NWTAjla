package com.medical.reviewservice.models.dto;

import com.medical.reviewservice.models.transients.Doctor;
import com.medical.reviewservice.models.transients.Pacijent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewDTO {

    private Long id;
    private Integer patientId;
    private Integer doctorID;
    private String comment;
    private Pacijent pacijent;
    private Doctor doctor;
}
