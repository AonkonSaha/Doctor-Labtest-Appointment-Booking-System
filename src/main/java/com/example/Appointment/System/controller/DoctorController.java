package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.DoctorNotFoundException;
import com.example.Appointment.System.exception.InvalidDoctorArgumentException;
import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.mapper.DoctorMapper;
import com.example.Appointment.System.service.DoctorService;
import com.example.Appointment.System.service.Imp.UserServiceImp;
import com.example.Appointment.System.service.UserValidationService;
import com.example.Appointment.System.service.ValidationService;
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
@RequestMapping(ApiPaths.Doctor.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Doctor API", description = "Manage doctor profiles")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final UserServiceImp userServiceImp;
    private final UserValidationService userValidationService;
    private final ValidationService validationService;

    @Operation(summary = "Register a new doctor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor registered successfully",
                    content = @Content(schema = @Schema(implementation = DoctorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid doctor data", content = @Content)
    })
    @PostMapping(ApiPaths.Doctor.REGISTER)
    public ResponseEntity<DoctorDTO> registerDoctor(@RequestBody DoctorDTO doctorDTO) {
        var validationErrors = validationService.validateDoctorDetails(doctorDTO);
        if (!validationErrors.isEmpty()) {
            throw new InvalidDoctorArgumentException(validationErrors);
        }
        var savedDoctor = doctorService.saveDoctor(doctorMapper.toDoctor(doctorDTO));
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(savedDoctor));
    }

    @Operation(summary = "Get a doctor by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor found",
                    content = @Content(schema = @Schema(implementation = DoctorDTO.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content)
    })
    @GetMapping(ApiPaths.Doctor.FETCH_BY_ID)
    public ResponseEntity<DoctorDTO> fetchDoctorById(
            @Parameter(description = "ID of the Doctor", example = "1001")
            @PathVariable("id") Long id)  {
        if (!userValidationService.isExitUserById(id)) {
            throw new DoctorNotFoundException("Doctor doesn't exist");
        }
        var doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(doctor));
    }

    @Operation(summary = "Update doctor details by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor updated successfully",
                    content = @Content(schema = @Schema(implementation = DoctorDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid doctor data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content)
    })
    @PutMapping(ApiPaths.Doctor.UPDATE)
    public ResponseEntity<DoctorDTO> updateDoctorById(
            @Parameter(description = "ID of the Doctor", example = "1001")
            @PathVariable("id") Long id,
            @RequestBody DoctorDTO doctorDTO) {
        if (!userValidationService.isExitUserById(id)) {
            throw new DoctorNotFoundException("Doctor doesn't exist");
        }
        var validationErrors = validationService.validateDoctorDetails(doctorDTO);
        if (!validationErrors.isEmpty()) {
            throw new InvalidDoctorArgumentException(validationErrors);
        }
        var updatedDoctor = doctorService.updateDoctorById(id, doctorDTO);
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(updatedDoctor));
    }

    @Operation(summary = "Delete a doctor by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Doctor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    @DeleteMapping(ApiPaths.Doctor.DELETE)
    public ResponseEntity<String> deleteDoctorById(
            @Parameter(description = "ID of the Doctor", example = "1001")
            @PathVariable("id") Long id) {
        if (!userValidationService.isExitUserById(id)) {
            throw new DoctorNotFoundException("Doctor doesn't exist");
        }
        doctorService.deleteDoctorByDoctorId(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @Operation(summary = "Get all doctors")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of doctors returned"),
            @ApiResponse(responseCode = "404", description = "No doctors found")
    })
    @GetMapping(ApiPaths.Doctor.FETCH_ALL)
    public ResponseEntity<Map<String, List<DoctorDTO>>> fetchAllDoctors() {
        var allDoctors = doctorService.getAllDoctor();
        if (allDoctors.isEmpty()) {
            throw new DoctorNotFoundException("Doctor doesn't exist");
        }
        var doctorDTOS = doctorMapper.toDoctorDTOs(allDoctors);
        return ResponseEntity.ok(Map.of("doctors", doctorDTOS));
    }
}
