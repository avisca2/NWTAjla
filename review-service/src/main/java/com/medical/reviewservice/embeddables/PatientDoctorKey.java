package com.medical.reviewservice.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDoctorKey implements Serializable {
    private static final Long serialVersionUID = 1L;
    @NonNull
    @Column(name = "patient_id")
    private Integer patientID;
    @NonNull
    @Column(name = "doctor_id")
    private Long doctorID;
}
