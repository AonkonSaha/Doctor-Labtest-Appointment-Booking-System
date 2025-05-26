package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.DiagnosticCenterNotFoundException;
import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.mapper.DiagnosticMapper;
import com.example.Appointment.System.service.DiagnosticCenterService;
import com.example.Appointment.System.service.DiagnosticCenterValidationService;
import com.example.Appointment.System.service.Imp.DiagnosticCenterServiceImp;
import com.example.Appointment.System.service.ValidationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/diagnostic/center")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DiagnosticController {
    private final DiagnosticCenterService diagnosticCenterService;
    private final DiagnosticMapper diagnosticMapper;
    private final DiagnosticCenterValidationService diagnosticCenterValidationService;
    private final ValidationService validationService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("End Point Test");
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerDiagnostic(DiagnosticDTO diagnosticDTO){
     if (!validationService.validateDiagnosisCenterDetails(diagnosticDTO).isEmpty()) {
         return ResponseEntity.badRequest().body(validationService.validateDiagnosisCenterDetails(diagnosticDTO));
     }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.saveDiagnosticCenter(
                        diagnosticMapper.toDiagnosticCenter(diagnosticDTO)
                )));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetchDiagnosticCenterById(@PathVariable Long id) throws DiagnosticCenterNotFoundException {
        if(!diagnosticCenterValidationService.isExitDianosticCenterById(id)){
            return ResponseEntity.badRequest().body("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.getDiagnosticCenterById(id))
        );
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteDiagnosticCenterById(@PathVariable Long id) throws DiagnosticCenterNotFoundException {
        if(!diagnosticCenterValidationService.isExitDianosticCenterById(id)){
            return ResponseEntity.badRequest().body("DiagnosticCenter doesn't exit");
        }
        diagnosticCenterService.removeDiagnosticCenter(id);
        return ResponseEntity.ok("DiagnosticCenter deleted successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDiagnosticCenterById(@PathVariable Long id,@RequestBody DiagnosticDTO diagnosticDTO) throws DiagnosticCenterNotFoundException {
        if(!diagnosticCenterValidationService.isExitDianosticCenterById(id)){
            return ResponseEntity.badRequest().body("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.updateDiagnosticCenter(id,diagnosticDTO))
        );
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<?> fetchAllDiagnosticCenter() throws DiagnosticCenterNotFoundException {
        if(diagnosticCenterService.getAllDiagnosticCenter().isEmpty()){
            return ResponseEntity.badRequest().body("Diagnostic Center doesn't exit");
        }
        return ResponseEntity.ok(
                Map.of("clinics",diagnosticMapper.toDiagnosticDTOS(diagnosticCenterService.getAllDiagnosticCenter())));
    }
}
