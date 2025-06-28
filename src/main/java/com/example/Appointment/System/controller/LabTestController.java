package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.InvalidLabTestArgumentException;
import com.example.Appointment.System.exception.LabTestNotFoundException;
import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.mapper.LabTestMapper;
import com.example.Appointment.System.service.LabTestService;
import com.example.Appointment.System.service.LabTestValidationService;
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
@RequestMapping(ApiPaths.LabTest.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Lab Test API", description = "Manage lab tests")
public class LabTestController {

    private final LabTestService labTestService;
    private final LabTestMapper labTestMapper;
    private final ValidationService validationService;
    private final LabTestValidationService labTestValidationService;

    @Operation(summary = "Create a new lab test")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lab test created successfully",
                    content = @Content(schema = @Schema(implementation = LabTestDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid lab test data", content = @Content)
    })
    @PostMapping(ApiPaths.LabTest.REGISTER)
    public ResponseEntity<LabTestDTO> registerLabTest(@RequestBody LabTestDTO labTestDTO) {
        var errors = validationService.validateLabTestDetails(labTestDTO);
        if (!errors.isEmpty()) {
            throw new InvalidLabTestArgumentException(errors);
        }
        var saved = labTestService.saveLabTest(labTestMapper.toLabTest(labTestDTO));
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(saved));
    }

    @Operation(summary = "Get lab test by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lab test found",
                    content = @Content(schema = @Schema(implementation = LabTestDTO.class))),
            @ApiResponse(responseCode = "404", description = "Lab test not found", content = @Content)
    })
    @GetMapping(ApiPaths.LabTest.FETCH_BY_ID)
    public ResponseEntity<LabTestDTO> fetchLabTestById(
            @Parameter(description = "ID of the lab test to retrieve", example = "1001")
            @PathVariable("id") Long id) {
        if (!labTestValidationService.isExitLabTestById(id)) {
            throw new LabTestNotFoundException("LabTest doesn't exist");
        }
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(labTestService.getLabTestById(id)));
    }

    @Operation(summary = "Delete lab test by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lab test deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Lab test not found")
    })
    @DeleteMapping(ApiPaths.LabTest.DELETE)
    public ResponseEntity<String> deleteLabTestById(
            @Parameter(description = "ID of the lab test to delete", example = "1001")
            @PathVariable("id") Long id) {
        if (!labTestValidationService.isExitLabTestById(id)) {
            throw new LabTestNotFoundException("LabTest doesn't exist");
        }
        labTestService.removeLabTestById(id);
        return ResponseEntity.ok("LabTest deleted successfully");
    }

    @Operation(summary = "Update lab test by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lab test updated successfully",
                    content = @Content(schema = @Schema(implementation = LabTestDTO.class))),
            @ApiResponse(responseCode = "404", description = "Lab test not found")
    })
    @PutMapping(ApiPaths.LabTest.UPDATE)
    public ResponseEntity<LabTestDTO> updateLabTestById(
            @Parameter(description = "ID of the lab test to update", example = "1001")
            @PathVariable("id") Long id,
            @RequestBody LabTestDTO labTestDTO) {

        if (!labTestValidationService.isExitLabTestById(id)) {
            throw new LabTestNotFoundException("LabTest doesn't exist");
        }

        var updated = labTestService.updateLabTest(id, labTestDTO);
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(updated));
    }

    @Operation(summary = "Get all lab tests")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of lab tests returned"),
            @ApiResponse(responseCode = "404", description = "No lab tests found")
    })
    @GetMapping(ApiPaths.LabTest.FETCH_ALL)
    public ResponseEntity<Map<String, List<LabTestDTO>>> fetchAllLabTests() {
        var all = labTestService.getAllLabTest();
        if (all.isEmpty()) {
            throw new LabTestNotFoundException("No lab tests exist");
        }
        return ResponseEntity.ok(Map.of("labTests", labTestMapper.toLabTestDTOS(all)));
    }
}
