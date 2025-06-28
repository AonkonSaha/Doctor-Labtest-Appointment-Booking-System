package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.model.dto.TimeSlotDTO;
import com.example.Appointment.System.service.TimeSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(ApiPaths.TimeSlot.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Time Slot API", description = "Manage doctor appointment time slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @Operation(summary = "Create a new time slot")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time slot created",
                    content = @Content(schema = @Schema(implementation = TimeSlotDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping(ApiPaths.TimeSlot.REGISTER)
    public ResponseEntity<TimeSlotDTO> registerTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO) {
        return ResponseEntity.ok(timeSlotService.saveTimeSlot(timeSlotDTO));
    }

    @Operation(summary = "Update existing time slot by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time slot updated",
                    content = @Content(schema = @Schema(implementation = TimeSlotDTO.class))),
            @ApiResponse(responseCode = "404", description = "Time slot not found", content = @Content)
    })
    @PutMapping(ApiPaths.TimeSlot.UPDATE)
    public ResponseEntity<TimeSlotDTO> updateTimeSlot(
            @Parameter(description = "ID of the time slot to update", example = "1")
            @PathVariable("id") Long id,
            @RequestBody TimeSlotDTO timeSlotDTO) {
        if (!timeSlotService.isExitTimeSlotById(id)) {
            throw new IllegalArgumentException("Time Slot doesn't exist");
        }
        return ResponseEntity.ok(timeSlotService.updateTimeSlotById(id, timeSlotDTO));
    }

    @Operation(summary = "Get time slot by default ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Time slot retrieved",
                    content = @Content(schema = @Schema(implementation = TimeSlotDTO.class)))
    })
    @GetMapping(ApiPaths.TimeSlot.FETCH)
    public ResponseEntity<Map<String, TimeSlotDTO>> getTimeSlot() {
        long timeSlotId = 1;
        return ResponseEntity.ok(Map.of("timeSlots", timeSlotService.getTimeSlot(timeSlotId)));
    }
}
