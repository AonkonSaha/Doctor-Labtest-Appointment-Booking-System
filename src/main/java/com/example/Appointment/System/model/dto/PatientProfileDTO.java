package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@Schema(name = "PatientProfile", description = "DTO representing patient profile details for update.")
public class PatientProfileDTO {

    @Schema(
            description = "Full name of the patient.",
            example = "John Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @Schema(
            description = "Contact number of the patient.",
            example = "+8801881264859",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String contact;

    @Schema(
            description = "Gender of the patient.",
            example = "Male",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String gender;

    @Schema(
            description = "Email address of the patient.",
            example = "john.doe@example.com"
    )
    private String email;

    @Schema(
            description = "Password for patient login. Should be securely stored.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    @Schema(
            description = "Date of birth of the patient.",
            example = "1985-10-20"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Schema(
            description = "Residential address of the patient.",
            example = "House 12, Road 3, Dhaka"
    )
    private String address;

    @Schema(
            description = "Profile picture file uploaded by the patient."
    )
    private MultipartFile profilePicture;
}
