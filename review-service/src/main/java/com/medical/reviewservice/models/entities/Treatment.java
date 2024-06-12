package com.medical.reviewservice.models.entities;

import com.medical.reviewservice.models.transients.Doctor;
import com.medical.reviewservice.models.transients.Pacijent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer patientId;

    @Column(nullable = false)
    private Integer doctorId;
    @Column
    private String description;

    @Transient
    private Pacijent pacijent;

    @Transient
    private Doctor doctor;

}
