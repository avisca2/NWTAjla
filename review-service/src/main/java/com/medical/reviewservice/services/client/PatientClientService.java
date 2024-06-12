package com.medical.reviewservice.services.client;

import com.medical.reviewservice.models.transients.Pacijent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RESERVATION-SERVICE")
public interface PatientClientService {
    @GetMapping("/Pacijent/GetById/{id}")
    Pacijent getPatientById(@PathVariable Integer id);
}
