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
@Schema(name = "Doctor", description = "DTO representing doctor details.")
public class DoctorDTO {

    @Schema(
            description = "Unique identifier of the doctor. System-generated; not required during creation.",
            example = "101",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Full name of the doctor.",
            example = "Dr. Aonkon Saha",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String doctorName;

    @Schema(
            description = "Doctor's professional designation.",
            example = "Consultant Cardiologist",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String designation;

    @Schema(
            description = "Contact phone number.",
            example = "+8801881264859",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String contactNumber;

    @Schema(
            description = "Email address of the doctor.",
            example = "dr.aonkon@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @Schema(
            description = "Medical license number.",
            example = "BMDC-2020-789",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String licenseNumber;

    @Schema(
            description = "Years of professional experience.",
            example = "12",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer yearsOfExperience;

    @Schema(
            description = "Hospital or clinic where the doctor practices.",
            example = "Medicare Hospital"
    )
    private String hospitalOrClinicName;

    @Schema(
            description = "Complete address of the doctor or clinic.",
            example = "House 12, Road 3, Dhaka"
    )
    private String address;

    @Schema(
            description = "Languages spoken by the doctor.",
            example = "English, Bengali"
    )
    private String languagesSpoken;

    @Schema(
            description = "Degrees or qualifications of the doctor.",
            example = "[\"MBBS\", \"FCPS\"]",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Set<String> degrees = new HashSet<>();

    @Schema(
            description = "Gender of the doctor.",
            example = "Male",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String gender;

    @Schema(
            description = "Availability status of the doctor.",
            example = "true"
    )
    private Boolean availabilityStatus;

    @Schema(
            description = "URL to the doctor's profile picture.",
            example = "https://example.com/images/doctor-profile.jpg"
    )
    private String profilePictureUrl;

    @Schema(
            description = "Average rating of the doctor out of 5.",
            example = "4.8"
    )
    private Double rating;

    @Schema(
            description = "Password for doctor login. Should be stored securely hashed.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    @Schema(
            description = "String representation of degrees for convenience or display.",
            example = "MBBS, FCPS"
    )
    private String degreesString;

    @Schema(
            description = "Date of birth of the doctor.",
            example = "1980-05-15"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
