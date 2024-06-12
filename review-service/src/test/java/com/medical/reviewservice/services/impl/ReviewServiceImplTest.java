package com.medical.reviewservice.services.impl;

import com.medical.reviewservice.exceptions.ResourceNotFoundException;
import com.medical.reviewservice.models.dto.ReviewDTO;
import com.medical.reviewservice.models.transients.Doctor;
import com.medical.reviewservice.models.transients.Pacijent;
import com.medical.reviewservice.models.entities.Review;
import com.medical.reviewservice.repositories.ReviewRepository;
import com.medical.reviewservice.services.client.DoctorClientService;
import com.medical.reviewservice.services.client.PatientClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {
    @InjectMocks
    private ReviewServiceImpl reviewService;
    private Review review;
    private ReviewDTO reviewDTO;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ModelMapper modelMapper;
    private Pacijent pacijent;
    private Doctor doctor;

    @Mock
    private PatientClientService patientClientService;

    @Mock
    private DoctorClientService doctorClientService;

    @BeforeEach
    public void setUp() {
        pacijent = Pacijent.builder()
                .id(1)
                .ime("patient1")
                .prezime("patient_surname")
                .build();
        doctor = Doctor.builder()
                .docId(1)
                .name("doctor1")
                .surrname("doctor_surname")
                .specialization("generaliste")
                .build();

        review = Review.builder()
                .id(1L)
                .patientId(1)
                .doctorId(1)
                .doctor(doctor)
                .pacijent(pacijent)
                .comment("good")
                .build();
        reviewDTO = ReviewDTO.builder()
                .id(1L)
                .doctorID(1)
                .patientId(1)
                .doctor(doctor)
                .pacijent(pacijent)
                .comment("good")
                .build();
    }

    @Test
    @DisplayName("Test find all method when the list is empty")
    public void testFindAllWhenListIsEmpty() {
        given(reviewRepository.findAll()).willReturn(Collections.emptyList());
        List<ReviewDTO> result = reviewService.findAll();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Test find all method when the list is not empty")
    public void testFindAllWhenListIsNotEmpty() {
        given(reviewRepository.findAll()).willReturn(List.of(review));
        given(modelMapper.map(review, ReviewDTO.class)).willReturn(reviewDTO);
        List<ReviewDTO> result = reviewService.findAll();
        assertThat(result).contains(reviewDTO);
        assertThat(result).hasSize(1);
    }

//    @Test
//    @DisplayName("Test save method when doctor id is not found")
//    public void testSaveWhenDoctorIsNotFound() {
//        given(modelMapper.map(reviewDTO, Review.class)).willReturn(review);
//        given(doctorClientService.getDoctorById(any(Integer.class))).willReturn(null);
//        assertThatExceptionOfType(ResourceNotFoundException.class)
//                .isThrownBy(() -> reviewService.save(reviewDTO))
//                .withMessage("Doctor not found with ID: " + reviewDTO.getDoctorID());
//    }
//
//    @Test
//    @DisplayName("Test save method when patient id is not found")
//    public void testSaveWhenPatientIsNotFound() {
//        given(modelMapper.map(reviewDTO, Review.class)).willReturn(review);
//        given(doctorClientService.getDoctorById(1)).willReturn(doctor);
//        given(patientClientService.getPatientById(1)).willReturn(null);
//        assertThatExceptionOfType(ResourceNotFoundException.class)
//                .isThrownBy(() -> reviewService.save(reviewDTO))
//                .withMessage("Patient not found with ID: " +reviewDTO.getPatientId());
//    }

    @Test
    @DisplayName("Test save method in a success scenario")
    public void testSaveSuccess() {
        given(modelMapper.map(reviewDTO, Review.class)).willReturn(review);
        given(modelMapper.map(review, ReviewDTO.class)).willReturn(reviewDTO);
        given(doctorClientService.getDoctorById(1)).willReturn(doctor);
        given(patientClientService.getPatientById(1)).willReturn(pacijent);
        given(reviewRepository.save(review)).willReturn(review);
        ReviewDTO result = reviewService.save(reviewDTO);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(reviewDTO);
    }

//    @Test
//    @DisplayName("Test find by doctor id method when doctor id is not valid")
//    public void testFindByDoctorIdWhenDoctorIdIsNotFound() {
//        given(doctorClientService.getDoctorById(1)).willReturn(null);
//        assertThatExceptionOfType(ResourceNotFoundException.class)
//                .isThrownBy(() -> reviewService.save(reviewDTO))
//                .withMessage("Doctor not found with ID: " +reviewDTO.getDoctorID());
//    }

    @Test
    @DisplayName("Test find by doctor id method when doctor id is valid")
    public void testFindByDoctorIdWhenDoctorIdIsValid() {
        given(doctorClientService.getDoctorById(1)).willReturn(doctor);
        given(reviewRepository.findByDoctorId(1)).willReturn(List.of(review));
        given(modelMapper.map(review, ReviewDTO.class)).willReturn(reviewDTO);
        List<ReviewDTO> result = reviewService.findByDoctorId(1);
        assertThat(result).contains(reviewDTO);
        assertThat(result).hasSize(1);
    }

}
