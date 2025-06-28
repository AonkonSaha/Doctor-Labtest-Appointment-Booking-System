package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Schema(name="DoctorAppointment", description = "DTO representing a doctor's appointment booking.")
public class DoctorBookingDTO {

    @Schema(
            description = "ID of the doctor for whom the appointment is being booked.",
            example = "101",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long doctorId;

    @Schema(
            description = "Date on which the appointment is scheduled.",
            example = "2025-07-01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;

    @Schema(
            description = "Time slot for the appointment (e.g., 10:30 AM - 11:00 AM).",
            example = "10:30 AM - 11:00 AM",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String slotTime;

    @Schema(
            description = "Unique identifier for the booking. System-generated; not required during creation.",
            example = "5001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long doctorBookingId;

    @Schema(
            description = "ID of the patient who booked the appointment.",
            example = "301"
    )
    private Long patientId;

    @Schema(
            description = "Full name of the doctor.",
            example = "Dr. Aonkon Saha"
    )
    private String doctorName;

    @Schema(
            description = "Medical license number of the doctor.",
            example = "BMDC-2020-789"
    )
    private String licenseNumber;

    @Schema(
            description = "Hospital or clinic name where the doctor practices.",
            example = "Medicare Hospital"
    )
    private String hospitalOrClinicName;

    @Schema(
            description = "Degrees or qualifications of the doctor.",
            example = "[\"MBBS\", \"FCPS\"]"
    )
    private Set<String> degrees = new HashSet<>();

    @Schema(
            description = "Designation of the doctor.",
            example = "Consultant Cardiologist"
    )
    private String designation;

    @Schema(
            description = "Optional note or message added by the patient.",
            example = "Please ensure ECG is available."
    )
    private String note;

    @Schema(
            description = "Status of the booking (e.g., PENDING, CONFIRMED, CANCELLED).",
            example = "PENDING"
    )
    private String status;

    @Schema(
            description = "Actual appointment date (can differ from booking date if rescheduled).",
            example = "2025-07-01"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    @Schema(
            description = "Profile image URL of the doctor.",
            example = "https://example.com/images/doctor-profile.jpg"
    )
    private String doctorImageUrl;

    @Schema(
            description = "Gender of the doctor.",
            example = "Male"
    )
    private String gender;

    @Schema(
            description = "Email address of the doctor.",
            example = "dr.aonkon@example.com"
    )
    private String email;

    @Schema(
            description = "Contact number of the doctor.",
            example = "+8801881264859"
    )
    private String contact;
}
