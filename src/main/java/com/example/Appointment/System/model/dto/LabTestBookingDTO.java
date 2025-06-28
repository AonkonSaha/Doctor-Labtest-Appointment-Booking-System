package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(name = "LabTestBooking", description = "DTO representing a lab test booking and diagnostic center details.")
public class LabTestBookingDTO {

    @Schema(
            description = "Unique identifier of the lab test booking. System-generated; not required during creation.",
            example = "1001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Identifier of the lab test being booked.",
            example = "2001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long labTestId;

    @Schema(
            description = "Identifier of the lab where the test will be performed.",
            example = "3001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long labId;

    @Schema(
            description = "Name of the lab test.",
            example = "Complete Blood Count"
    )
    private String labTestName;

    @Schema(
            description = "Optional note or instructions related to the booking.",
            example = "Please provide fasting instructions."
    )
    private String note;

    @Schema(
            description = "Status of the lab test booking (e.g., PENDING, COMPLETED).",
            example = "PENDING"
    )
    private String status;

    @Schema(
            description = "Date when the lab test order was placed.",
            example = "2025-07-01",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate oderDate;

    @Schema(
            description = "Expected or actual delivery date of the test results.",
            example = "2025-07-05"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;

    @Schema(
            description = "Name of the diagnostic center where the test is booked.",
            example = "MediTech Diagnostic Center"
    )
    private String diagnosticCenterName;

    @Schema(
            description = "Country where the diagnostic center is located.",
            example = "Bangladesh"
    )
    private String country;

    @Schema(
            description = "City of the diagnostic center.",
            example = "Dhaka"
    )
    private String city;

    @Schema(
            description = "Postal code of the diagnostic center location.",
            example = "1209"
    )
    private String zipCode;

    @Schema(
            description = "Street address of the diagnostic center.",
            example = "House 12, Road 3"
    )
    private String address;

    @Schema(
            description = "Road number of the diagnostic center.",
            example = "3"
    )
    private String roadNo;

    @Schema(
            description = "Holding number of the diagnostic center.",
            example = "12A"
    )
    private String holdingNo;

    @Schema(
            description = "Primary contact number of the diagnostic center.",
            example = "+8801881264859"
    )
    private String contactNumber;

    @Schema(
            description = "Email address of the diagnostic center.",
            example = "info@meditech.com"
    )
    private String email;

    @Schema(
            description = "Operating hours of the diagnostic center.",
            example = "9:00 AM - 9:00 PM"
    )
    private String operatingHours;

    @Schema(
            description = "Accreditation details of the diagnostic center, if any.",
            example = "ISO 15189 Certified"
    )
    private String accreditation;

    @Schema(
            description = "Official website URL of the diagnostic center.",
            example = "https://meditech.com"
    )
    private String websiteUrl;

    @Schema(
            description = "Average rating of the diagnostic center out of 5.",
            example = "4.5"
    )
    private Double rating;

    @Schema(
            description = "Indicates whether the diagnostic center is currently active.",
            example = "true"
    )
    private Boolean isActive;

    @Schema(
            description = "URL to an image representing the lab test.",
            example = "https://example.com/images/labtest.jpg"
    )
    private String labTestImageUrl;
}
