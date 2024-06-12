package com.medical.reviewservice.services;

import com.medical.reviewservice.models.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

     ReviewDTO save(ReviewDTO reviewDTO);
     List<ReviewDTO> findAll();
     List<ReviewDTO> findByDoctorId(Integer doctorId);
}
