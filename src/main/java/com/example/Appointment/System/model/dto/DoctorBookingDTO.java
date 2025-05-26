package com.example.Appointment.System.model.dto;

import com.example.Appointment.System.model.entity.DoctorProfile;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
public class DoctorBookingDTO {
    private Long doctorId;
    private Long patientId;
    private Long doctorBookingId;
    private String doctorName;
    private String licenseNumber;
    private String hospitalOrClinicName;
    private Set<String> degrees=new HashSet<>();
    private String designation;
    private String note;
    private String status;
    private LocalDate bookingDate;
    private LocalDate appointmentDate;
    private String slotTime;
    private String doctorImageUrl;
    private String gender;
    private String email;
    private String contact;



}
