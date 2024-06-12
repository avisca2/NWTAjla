package com.medical.reviewservice.repositories;

import com.medical.reviewservice.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByDoctorId(Integer doctorId);
}
