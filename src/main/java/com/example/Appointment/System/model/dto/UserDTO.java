package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@Schema(name = "User", description = "DTO representing user details, including profile and professional info.")
public class UserDTO {

    @Schema(
            description = "Full name of the user.",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @Schema(
            description = "Contact phone number of the user.",
            example = "+8801881264859",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String contact;

    @Schema(
            description = "Gender of the user.",
            example = "Male",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String gender;

    @Schema(
            description = "Email address of the user.",
            example = "john.doe@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @Schema(
            description = "Password for the user login.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    @Schema(
            description = "Date of birth of the user.",
            example = "1985-10-20"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Schema(
            description = "URL to the profile picture of the user.",
            example = "https://example.com/images/user-profile.jpg"
    )
    private String profilePictureUrl;

    @Schema(
            description = "Residential address of the user.",
            example = "House 12, Road 3, Dhaka"
    )
    private String address;

    @Schema(
            description = "Profession of the user.",
            example = "Doctor"
    )
    private String profession;

    @Schema(
            description = "Professional designation.",
            example = "Consultant Cardiologist"
    )
    private String designation;

    @Schema(
            description = "License number, if applicable.",
            example = "BMDC-2020-789"
    )
    private String licenseNumber;

    @Schema(
            description = "Years of professional experience.",
            example = "12"
    )
    private Integer yearsOfExperience;

    @Schema(
            description = "Hospital or clinic name where the user practices.",
            example = "Medicare Hospital"
    )
    private String hospitalOrClinicName;

    @Schema(
            description = "Languages spoken by the user.",
            example = "English, Bengali"
    )
    private String languagesSpoken;

    @Schema(
            description = "Set of degrees or qualifications.",
            example = "[\"MBBS\", \"FCPS\"]"
    )
    private Set<String> degrees;

    @Schema(
            description = "Average rating of the user, out of 5.",
            example = "4.8"
    )
    private Double rating;

    @Schema(
            description = "Indicates if the user is currently available.",
            example = "true"
    )
    private Boolean availabilityStatus;
}
