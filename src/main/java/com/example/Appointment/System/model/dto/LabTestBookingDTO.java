package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class LabTestBookingDTO {
    private Long id;
    private Long labTestId;
    private Long labId;
    private String labTestName;
    private String note;
    private String status;
    private LocalDate  oderDate;
    private LocalDate deliveryDate;
    private String diagnosticCenterName;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String roadNo;
    private String holdingNo;
    private String contactNumber;
    private String email;
    private String operatingHours;
    private String accreditation;
    private String websiteUrl;
    private Double rating;
    private Boolean isActive;
    private String labTestImageUrl;


}
