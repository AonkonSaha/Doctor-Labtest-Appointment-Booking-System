package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.DoctorBookNotFoundException;
import com.example.Appointment.System.exception.DoctorNotFoundException;
import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.mapper.DoctorBookingMapper;
import com.example.Appointment.System.service.DoctorBookingService;
import com.example.Appointment.System.service.UserValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPaths.DoctorBooking.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Doctor Booking API", description = "Manage doctor appointment bookings")
public class DoctorBookingController {

    private final DoctorBookingService doctorBookingService;
    private final DoctorBookingMapper doctorBookingMapper;
    private final UserValidationService userValidationService;

    @Operation(summary = "Create a new doctor booking")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor booking created successfully",
                    content = @Content(schema = @Schema(implementation = DoctorBookingDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid booking data", content = @Content)
    })
    @PostMapping(ApiPaths.DoctorBooking.REGISTER)
    public ResponseEntity<DoctorBookingDTO> registerDoctorBooking(@RequestBody DoctorBookingDTO doctorBookingDTO){
        var saved = doctorBookingService.saveDoctorBooking(doctorBookingMapper.toDoctorBooking(doctorBookingDTO));
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(saved));
    }

    @Operation(summary = "Get a doctor booking by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor booking found",
                    content = @Content(schema = @Schema(implementation = DoctorBookingDTO.class))),
            @ApiResponse(responseCode = "404", description = "Doctor booking not found", content = @Content)
    })
    @GetMapping(ApiPaths.DoctorBooking.FETCH_BY_ID)
    public ResponseEntity<DoctorBookingDTO> fetchDoctorBookingById(
            @Parameter(description = "ID of the DoctorBooking", example = "1001")
            @PathVariable("id") Long id) {
        if (!doctorBookingService.isExitDoctorBookById(id)) {
            throw new DoctorBookNotFoundException("Doctor booking doesn't exist");
        }
        var booking = doctorBookingService.fetchDoctorBookById(id);
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(booking));
    }

    @Operation(summary = "Delete a doctor booking by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor booking deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Doctor booking not found")
    })
    @DeleteMapping(ApiPaths.DoctorBooking.DELETE)
    public ResponseEntity<String> deleteDoctorBookingById(
            @Parameter(description = "ID of the DoctorBooking", example = "1001")
            @PathVariable("id") Long id)  {
        if (!doctorBookingService.isExitDoctorBookById(id)) {
            throw new DoctorBookNotFoundException("Doctor booking doesn't exist");
        }
        doctorBookingService.deleteDoctorBookById(id);
        return ResponseEntity.ok("Doctor booking deleted successfully");
    }

    @Operation(summary = "Update a doctor booking by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor booking updated successfully",
                    content = @Content(schema = @Schema(implementation = DoctorBookingDTO.class))),
            @ApiResponse(responseCode = "404", description = "Doctor booking not found", content = @Content)
    })
    @PutMapping(ApiPaths.DoctorBooking.UPDATE)
    public ResponseEntity<DoctorBookingDTO> updateDoctorBookingById(
            @Parameter(description = "ID of the DoctorBooking", example = "1001")
            @PathVariable("id") Long id,
            @RequestBody DoctorBookingDTO doctorBookingDTO){
        if (!doctorBookingService.isExitDoctorBookById(id)) {
            throw new DoctorBookNotFoundException("Doctor booking doesn't exist");
        }
        var updated = doctorBookingService.updateDoctorBooking(id, doctorBookingDTO);
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(updated));
    }

    @Operation(summary = "Get all doctor bookings")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of doctor bookings"),
            @ApiResponse(responseCode = "404", description = "No doctor bookings found")
    })
    @GetMapping(ApiPaths.DoctorBooking.FETCH_ALL)
    public ResponseEntity<List<DoctorBookingDTO>> fetchAllDoctorBookings() {
        var allBookings = doctorBookingService.fetchAllDoctorBooking();
        if (allBookings.isEmpty()) {
            throw new DoctorBookNotFoundException("No doctor bookings exist");
        }
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTOS(allBookings));
    }

    @Operation(summary = "Get booked time slots for a doctor on a given date")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booked time slots returned"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
            @ApiResponse(responseCode = "400", description = "Invalid date parameter")
    })
    @GetMapping(ApiPaths.DoctorBooking.TIME_SLOT)
    public ResponseEntity<Map<String, List<String>>> fetchTimeSlotDoctorBooking(
            @Parameter(description = "ID of the Doctor", example = "1001")
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (!userValidationService.isExitUserById(doctorId)) {
            throw new DoctorNotFoundException("Doctor doesn't exist");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date is null");
        }
        var bookedSlots = doctorBookingService.getTimeSlotDoctorBooking(doctorId, date);
        return ResponseEntity.ok(Map.of("bookedSlots", bookedSlots));
    }

    @Operation(summary = "Get doctor booking history of the current user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking history returned")
    })
    @GetMapping(ApiPaths.DoctorBooking.HISTORY)
    public ResponseEntity<Map<String, List<DoctorBookingDTO>>> fetchDoctorBookingHistoryByUser() {
        var history = doctorBookingService.getDoctorBookingHistory();
        return ResponseEntity.ok(Map.of("doctorBookingHistories", doctorBookingMapper.toDoctorBookingDTOS(history)));
    }
}
