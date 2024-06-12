package com.medical.reviewservice.models.entities;

import com.medical.reviewservice.models.transients.Doctor;
import com.medical.reviewservice.models.transients.Pacijent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String comment;
    @Column(nullable = false)
    private Integer patientId;

    @Column(nullable = false)
    private Integer doctorId;

    @Transient
    private Pacijent pacijent;

    @Transient
    private Doctor doctor;
}
