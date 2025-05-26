package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Builder
public class PatientDTO {
    @JsonProperty(value = "patient_name")
    @NotNull
    private String patientName;
    @JsonProperty(value = "mobile_number")
    @NotNull
    private String mobileNumber;
    private String email;
    @NotNull
    private String gender;
    @JsonProperty(value = "date_of_birth")
    private LocalDate dateOfBirth;
    @NotNull
    private String password;
    private String role;
    private String profilePictureUrl;
    private String address;

}
