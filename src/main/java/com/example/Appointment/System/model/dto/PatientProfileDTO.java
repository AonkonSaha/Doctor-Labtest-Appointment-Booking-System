package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
public class PatientProfileDTO {
    @NotNull
    private String name;
    @NotNull
    private String contact;
    @NotNull
    private String gender;
    private String email;
    @NotNull
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String address;
    private MultipartFile profilePicture;
}
