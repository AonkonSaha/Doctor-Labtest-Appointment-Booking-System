package com.example.Appointment.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "TimeSlot", description = "DTO representing time slot configuration.")
public class TimeSlotDTO {

    @Schema(
            description = "Number of available slots.",
            example = "5",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long numberOfSlots;

    @Schema(
            description = "Duration of each slot in minutes.",
            example = "30",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long durable;

    @Schema(
            description = "Starting hour for the slots in 24-hour format.",
            example = "9",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long startHour;
}
