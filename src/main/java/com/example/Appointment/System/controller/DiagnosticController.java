package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.DiagnosticCenterNotFoundException;
import com.example.Appointment.System.exception.InvalidDiagnosticCenterArgumentException;
import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.mapper.DiagnosticMapper;
import com.example.Appointment.System.service.DiagnosticCenterService;
import com.example.Appointment.System.service.DiagnosticCenterValidationService;
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
@RequestMapping(ApiPaths.DiagnosticCenter.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Diagnostic Center API", description = "Manage diagnostic centers")
public class DiagnosticController {

    private final DiagnosticCenterService diagnosticCenterService;
    private final DiagnosticMapper diagnosticMapper;
    private final DiagnosticCenterValidationService diagnosticCenterValidationService;
    private final ValidationService validationService;

    @Operation(summary = "Register a new diagnostic center",
            description = "Creates a new diagnostic center with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnostic center registered successfully",
                    content = @Content(schema = @Schema(implementation = DiagnosticDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid diagnostic center data supplied",
                    content = @Content)
    })
    @PostMapping(ApiPaths.DiagnosticCenter.REGISTER)
    public ResponseEntity<DiagnosticDTO> registerDiagnostic(@RequestBody DiagnosticDTO diagnosticDTO) {
        var validationErrors = validationService.validateDiagnosisCenterDetails(diagnosticDTO);
        if (!validationErrors.isEmpty()) {
            throw new InvalidDiagnosticCenterArgumentException(validationErrors);
        }
        var savedCenter = diagnosticCenterService.saveDiagnosticCenter(diagnosticMapper.toDiagnosticCenter(diagnosticDTO));
        return ResponseEntity.ok(diagnosticMapper.toDiagnosticDTO(savedCenter));
    }

    @Operation(summary = "Fetch diagnostic center by ID",
            description = "Retrieves diagnostic center details for the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnostic center found",
                    content = @Content(schema = @Schema(implementation = DiagnosticDTO.class))),
            @ApiResponse(responseCode = "404", description = "Diagnostic center not found",
                    content = @Content)
    })
    @GetMapping(ApiPaths.DiagnosticCenter.FETCH_BY_ID)
    public ResponseEntity<DiagnosticDTO> fetchDiagnosticCenterById(
            @Parameter(description = "ID of the DiagnosticCenter", example = "1001")
            @PathVariable Long id) {
        if (!diagnosticCenterValidationService.isExitDianosticCenterById(id)) {
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exist");
        }
        var diagnosticCenter = diagnosticCenterService.getDiagnosticCenterById(id);
        return ResponseEntity.ok(diagnosticMapper.toDiagnosticDTO(diagnosticCenter));
    }

    @Operation(summary = "Delete diagnostic center by ID",
            description = "Deletes the diagnostic center with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnostic center deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Diagnostic center not found")
    })
    @DeleteMapping(ApiPaths.DiagnosticCenter.DELETE)
    public ResponseEntity<String> deleteDiagnosticCenterById(
            @Parameter(description = "ID of the DiagnosticCenter", example = "1001")
            @PathVariable Long id) {
        if (!diagnosticCenterValidationService.isExitDianosticCenterById(id)) {
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exist");
        }
        diagnosticCenterService.removeDiagnosticCenter(id);
        return ResponseEntity.ok("DiagnosticCenter deleted successfully");
    }

    @Operation(summary = "Update diagnostic center by ID",
            description = "Updates the details of the diagnostic center with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diagnostic center updated successfully",
                    content = @Content(schema = @Schema(implementation = DiagnosticDTO.class))),
            @ApiResponse(responseCode = "404", description = "Diagnostic center not found")
    })
    @PutMapping(ApiPaths.DiagnosticCenter.UPDATE)
    public ResponseEntity<DiagnosticDTO> updateDiagnosticCenterById(
            @Parameter(description = "ID of the DiagnosticCenter", example = "1001")
            @PathVariable Long id,
            @RequestBody DiagnosticDTO diagnosticDTO) {
        if (!diagnosticCenterValidationService.isExitDianosticCenterById(id)) {
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exist");
        }
        var updatedCenter = diagnosticCenterService.updateDiagnosticCenter(id, diagnosticDTO);
        return ResponseEntity.ok(diagnosticMapper.toDiagnosticDTO(updatedCenter));
    }

    @Operation(summary = "Fetch all diagnostic centers",
            description = "Retrieves all available diagnostic centers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of diagnostic centers returned",
                    content = @Content(schema = @Schema(implementation = DiagnosticDTO.class))),
            @ApiResponse(responseCode = "404", description = "No diagnostic centers found")
    })
    @GetMapping(ApiPaths.DiagnosticCenter.FETCH_ALL)
    public ResponseEntity<Map<String, List<DiagnosticDTO>>> fetchAllDiagnosticCenter() {
        var centers = diagnosticCenterService.getAllDiagnosticCenter();
        if (centers.isEmpty()) {
            throw new DiagnosticCenterNotFoundException("No diagnostic centers found");
        }
        return ResponseEntity.ok(Map.of("clinics", diagnosticMapper.toDiagnosticDTOS(centers)));
    }
}
