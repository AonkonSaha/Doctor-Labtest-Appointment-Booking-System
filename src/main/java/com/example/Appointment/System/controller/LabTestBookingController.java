package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.LabTestBookingNotFoundException;
import com.example.Appointment.System.model.dto.LabTestBookingDTO;
import com.example.Appointment.System.model.mapper.LabTestBookingMapper;
import com.example.Appointment.System.service.LabTestBookingService;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPaths.LabTestBooking.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Lab Test Booking API", description = "Manage lab test bookings")
public class LabTestBookingController {

    private final LabTestBookingService labTestBookingService;
    private final LabTestBookingMapper labTestBookingMapper;

    @Operation(summary = "Register a new lab test booking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking created successfully",
                    content = @Content(schema = @Schema(implementation = LabTestBookingDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content)
    })
    @PostMapping(ApiPaths.LabTestBooking.REGISTER)
    public ResponseEntity<LabTestBookingDTO> registerLabTestBooking(@RequestBody LabTestBookingDTO labTestBookingDTO) {
        var saved = labTestBookingService.saveLabTestBooking(labTestBookingMapper.toLabTestBooking(labTestBookingDTO));
        return ResponseEntity.ok(labTestBookingMapper.toLabTestBookingDTO(saved));
    }

    @Operation(summary = "Get a booking by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking retrieved",
                    content = @Content(schema = @Schema(implementation = LabTestBookingDTO.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = @Content)
    })
    @GetMapping(ApiPaths.LabTestBooking.FETCH_BY_ID)
    public ResponseEntity<LabTestBookingDTO> fetchLabTestBookingById(
            @Parameter(description = "ID of the LabTestBooking", example = "1001")
            @PathVariable("id") Long id) {
        if (!labTestBookingService.isExitLabTestBookingById(id)) {
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exist");
        }
        return ResponseEntity.ok(
                labTestBookingMapper.toLabTestBookingDTO(labTestBookingService.getLabTestBookingById(id))
        );
    }

    @Operation(summary = "Delete a booking by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking deleted"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @DeleteMapping(ApiPaths.LabTestBooking.DELETE)
    public ResponseEntity<String> deleteLabTestBookingById(
            @Parameter(description = "ID of the LabTestBooking", example = "1001")
            @PathVariable Long id) {
        if (!labTestBookingService.isExitLabTestBookingById(id)) {
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exist");
        }
        labTestBookingService.removeLabTestBookingById(id);
        return ResponseEntity.ok("LabTestBooking deleted successfully");
    }

    @Operation(summary = "Update a booking by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking updated",
                    content = @Content(schema = @Schema(implementation = LabTestBookingDTO.class))),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @PutMapping(ApiPaths.LabTestBooking.UPDATE)
    public ResponseEntity<LabTestBookingDTO> updateLabTestBookingById(
            @Parameter(description = "ID of the LabTestBooking", example = "1001")
            @PathVariable("id") Long id,
            @RequestBody LabTestBookingDTO labTestBookingDTO) {

        if (!labTestBookingService.isExitLabTestBookingById(id)) {
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exist");
        }

        var updated = labTestBookingService.modifyLabTestBookingById(id, labTestBookingDTO);
        return ResponseEntity.ok(labTestBookingMapper.toLabTestBookingDTO(updated));
    }

    @Operation(summary = "Get all lab test bookings")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All bookings fetched"),
            @ApiResponse(responseCode = "404", description = "No bookings found")
    })
    @GetMapping(ApiPaths.LabTestBooking.FETCH_ALL)
    public ResponseEntity<List<LabTestBookingDTO>> fetchAllLabTestBookings() {
        var all = labTestBookingService.getAllLabTestBooking();
        if (all.isEmpty()) {
            throw new LabTestBookingNotFoundException("No lab test bookings exist");
        }
        return ResponseEntity.ok(labTestBookingMapper.toLabTestBookingDTOS(all));
    }

    @Operation(summary = "Get booking history of the current user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking history returned")
    })
    @GetMapping(ApiPaths.LabTestBooking.HISTORY)
    public ResponseEntity<Map<String, List<LabTestBookingDTO>>> fetchAllLabTestBookingHistoryByUser() {
        var history = labTestBookingService.getAllLabTestBookHistoryByUser();
        return ResponseEntity.ok(Map.of("labTestsBook", labTestBookingMapper.toLabTestBookingDTOS(history)));
    }
}
