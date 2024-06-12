package com.medical.reviewservice.models.transients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    private Integer docId;
    private String name;
    private String surrname;
    private String specialization;
}
