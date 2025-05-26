package com.example.Appointment.System.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiagnosticDTO {
    private Long labId;
    @NotNull
    private String diagnosticCenterName;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String zipCode;
    @NotNull
    private String address;
    @NotNull
    private String roadNo;
    @NotNull
    private String holdingNo;
    private String profilePictureUrl;
    @NotNull
    private String contactNumber;
    @NotNull
    private String email;
    private String operatingHours;
    private String accreditation;
    private String websiteUrl;
    private Double rating;
    private Boolean isActive;
}
