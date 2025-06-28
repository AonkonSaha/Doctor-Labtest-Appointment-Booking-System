package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(name = "Patient", description = "DTO representing patient details.")
public class PatientDTO {

    @Schema(
            description = "Full name of the patient.",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("patient_name")
    private String patientName;

    @Schema(
            description = "Mobile number of the patient.",
            example = "+8801881264859",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("mobile_number")
    private String mobileNumber;

    @Schema(
            description = "Email address of the patient.",
            example = "john.doe@example.com"
    )
    private String email;

    @Schema(
            description = "Gender of the patient.",
            example = "Male",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String gender;

    @Schema(
            description = "Date of birth of the patient.",
            example = "1985-10-20"
    )
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @Schema(
            description = "Password for patient login. Should be stored securely hashed.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    @Schema(
            description = "Role assigned to the patient user.",
            example = "ROLE_PATIENT"
    )
    private String role;

    @Schema(
            description = "URL to the profile picture of the patient.",
            example = "https://example.com/images/patient-profile.jpg"
    )
    private String profilePictureUrl;

    @Schema(
            description = "Residential address of the patient.",
            example = "House 12, Road 3, Dhaka"
    )
    private String address;
}
