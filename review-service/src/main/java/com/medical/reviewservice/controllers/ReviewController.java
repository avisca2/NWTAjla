package com.medical.reviewservice.controllers;

import com.medical.reviewservice.models.dto.ReviewDTO;
import com.medical.reviewservice.services.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "api/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.save(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviewDTOS = reviewService.findAll();
        return ResponseEntity.ok(reviewDTOS);
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<ReviewDTO>> getDoctorReviews(@PathVariable Integer id) {
        List<ReviewDTO> reviewDTOS = reviewService.findByDoctorId(id);
        return ResponseEntity.ok(reviewDTOS);
    }
}
