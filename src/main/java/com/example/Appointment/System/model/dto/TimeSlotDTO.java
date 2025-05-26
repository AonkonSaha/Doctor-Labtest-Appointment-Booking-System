package com.example.Appointment.System.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSlotDTO {
    @NotNull
    private Long numberOfSlots;
    @NotNull
    private Long durable;
    @NotNull
    private Long startHour;
}
