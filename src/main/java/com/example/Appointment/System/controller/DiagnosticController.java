package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.DiagnosticCenterNotFoundException;
import com.example.Appointment.System.exception.InvalidDiagnosticCenterArgumentException;
import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.mapper.DiagnosticMapper;
import com.example.Appointment.System.service.DiagnosticCenterService;
import com.example.Appointment.System.service.DiagnosticCenterValidationService;
import com.example.Appointment.System.service.ValidationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import static com.example.Appointment.System.constant.ApiPaths.DiagnosticCenter;

@RestController
@RequestMapping(DiagnosticCenter.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DiagnosticController {
    private final DiagnosticCenterService diagnosticCenterService;
    private final DiagnosticMapper diagnosticMapper;
    private final DiagnosticCenterValidationService diagnosticCenterValidationService;
    private final ValidationService validationService;

    @PostMapping(DiagnosticCenter.REGISTER)
    public ResponseEntity<DiagnosticDTO> registerDiagnostic(@RequestBody DiagnosticDTO diagnosticDTO){
        if (!validationService.validateDiagnosisCenterDetails(diagnosticDTO).isEmpty()) {
            throw new InvalidDiagnosticCenterArgumentException(validationService.validateDiagnosisCenterDetails(diagnosticDTO));
        }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.saveDiagnosticCenter(
                        diagnosticMapper.toDiagnosticCenter(diagnosticDTO)
                )));
    }

    @GetMapping(DiagnosticCenter.FETCH_BY_ID)
    public ResponseEntity<DiagnosticDTO> fetchDiagnosticCenterById(@PathVariable Long id)  {
        if(!diagnosticCenterValidationService.isExitDianosticCenterById(id)){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.getDiagnosticCenterById(id))
        );
    }

    @DeleteMapping(DiagnosticCenter.DELETE)
    public ResponseEntity<String> deleteDiagnosticCenterById(@PathVariable Long id) {
        if(!diagnosticCenterValidationService.isExitDianosticCenterById(id)){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        diagnosticCenterService.removeDiagnosticCenter(id);
        return ResponseEntity.ok("DiagnosticCenter deleted successfully");
    }

    @PutMapping(DiagnosticCenter.UPDATE)
    public ResponseEntity<DiagnosticDTO> updateDiagnosticCenterById(@PathVariable Long id,@RequestBody DiagnosticDTO diagnosticDTO) {
        if(!diagnosticCenterValidationService.isExitDianosticCenterById(id)){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.updateDiagnosticCenter(id,diagnosticDTO))
        );
    }

    @GetMapping(DiagnosticCenter.FETCH_ALL)
    public ResponseEntity<Map<String,List<DiagnosticDTO>>> fetchAllDiagnosticCenter() {
        if(diagnosticCenterService.getAllDiagnosticCenter().isEmpty()){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                Map.of("clinics",diagnosticMapper.toDiagnosticDTOS(diagnosticCenterService.getAllDiagnosticCenter())));
    }
}
