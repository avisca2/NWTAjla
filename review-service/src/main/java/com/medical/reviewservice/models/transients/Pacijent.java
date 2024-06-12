package com.medical.reviewservice.models.transients;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pacijent {

    private Integer id;

    private String ime;

    private String prezime;

    private Boolean samUSobi;

}
