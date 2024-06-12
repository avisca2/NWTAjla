package com.medical.reviewservice.services.client;

import com.medical.reviewservice.models.transients.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TIT-SERVICE")
public interface DoctorClientService {

    @GetMapping("/doktori/{id}")
    Doctor getDoctorById(@PathVariable Integer id);
}
