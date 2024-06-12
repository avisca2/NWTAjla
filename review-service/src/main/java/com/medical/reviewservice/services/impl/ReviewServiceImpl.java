package com.medical.reviewservice.services.impl;

import com.medical.reviewservice.exceptions.ResourceNotFoundException;
import com.medical.reviewservice.models.dto.ReviewDTO;
import com.medical.reviewservice.models.entities.Review;
import com.medical.reviewservice.repositories.ReviewRepository;
import com.medical.reviewservice.services.ReviewService;
import com.medical.reviewservice.services.client.DoctorClientService;
import com.medical.reviewservice.services.client.PatientClientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final PatientClientService patientClientService;
    private final DoctorClientService doctorClientService;

    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setDoctor(doctorClientService.getDoctorById(reviewDTO.getDoctorID()));
        review.setPacijent(patientClientService.getPatientById(reviewDTO.getPatientId()));
        review.setDoctorId(reviewDTO.getDoctorID());
        Review savedReview = reviewRepository.save(review);
        return modelMapper.map(savedReview, ReviewDTO.class);
    }


    @Override
    public List<ReviewDTO> findAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).toList();
    }

    @Override
    public List<ReviewDTO> findByDoctorId(Integer doctorId) {
        doctorClientService.getDoctorById(doctorId);
        List<Review> reviews = reviewRepository.findByDoctorId(doctorId);
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("No reviews found for doctor with ID: " + doctorId);
        }
        return reviews.stream().map(review -> {
            review.setDoctor(doctorClientService.getDoctorById(doctorId));
            review.setPacijent(patientClientService.getPatientById(review.getPatientId()));
            return modelMapper.map(review, ReviewDTO.class);
        }).collect(Collectors.toList());
    }
}
