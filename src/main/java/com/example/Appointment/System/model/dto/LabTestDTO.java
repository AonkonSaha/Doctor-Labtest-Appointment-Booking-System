package com.example.Appointment.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "LabTest", description = "DTO representing a laboratory test.")
public class LabTestDTO {

    @Schema(
            description = "Unique identifier of the lab test. System-generated; not required during creation.",
            example = "101",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Name of the lab test.",
            example = "Complete Blood Count",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String labTestName;

    @Schema(
            description = "Detailed description of the lab test.",
            example = "A test to evaluate overall health and detect a wide range of disorders."
    )
    private String description;

    @Schema(
            description = "URL to an image representing the lab test.",
            example = "https://example.com/images/labtest.jpg"
    )
    private String labTestImageUrl;
}
