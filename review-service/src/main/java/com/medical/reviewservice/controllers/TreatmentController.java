package com.medical.reviewservice.controllers;

import com.medical.reviewservice.models.dto.TreatmentDTO;
import com.medical.reviewservice.services.TreatmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/treatments", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@AllArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    @PostMapping
    public ResponseEntity<TreatmentDTO> createTreatment(@Valid @RequestBody TreatmentDTO treatmentDTO) {
        TreatmentDTO createdTreatment = treatmentService.save(treatmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTreatment);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentDTO>> getAllTreatments() {
        List<TreatmentDTO> treatmentDTOS = treatmentService.findAll();
        return ResponseEntity.ok(treatmentDTOS);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<TreatmentDTO>> getPatientTreatments(@PathVariable Integer id) {
        List<TreatmentDTO> treatmentDTOS = treatmentService.findByPatientId(id);
        return ResponseEntity.ok(treatmentDTOS);
    }
}
