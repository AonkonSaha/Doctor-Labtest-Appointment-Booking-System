package com.example.Appointment.System.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabTestDTO {
    private Long id;
    @NotNull
    private String labTestName;
    private String description;
    private String labTestImageUrl;
}
