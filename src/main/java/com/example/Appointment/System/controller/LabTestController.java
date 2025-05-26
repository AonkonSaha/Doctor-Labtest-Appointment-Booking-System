package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.LabTestNotFoundException;
import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.mapper.LabTestMapper;
import com.example.Appointment.System.service.Imp.LabTestServiceImp;
import com.example.Appointment.System.service.LabTestService;
import com.example.Appointment.System.service.LabTestValidationService;
import com.example.Appointment.System.service.ValidationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lab/test")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LabTestController {
    private final LabTestService labTestService;
    private final LabTestMapper labTestMapper;
    private final ValidationService validationService;
    private final LabTestValidationService labTestValidationService;
    @RequestMapping("/test")
    public String test(){
        return "End Point Test";
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerLabTest(@RequestBody LabTestDTO labTestDTO){
        if(!validationService.validateLabTestDetails(labTestDTO).isEmpty()){
            return ResponseEntity.badRequest().body(validationService.validateLabTestDetails(labTestDTO));
        }
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(labTestService.saveLabTest(
                        labTestMapper.toLabTest(labTestDTO))));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<LabTestDTO> fetchLabTestById(@RequestBody Long id){
        if(!labTestValidationService.isExitLabTestById(id)){
            throw new LabTestNotFoundException("LabTest doesn't exit");
        }
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(
                labTestService.getLabTestById(id)
        ));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLabTestById(@PathVariable("id") Long id){
        if(!labTestValidationService.isExitLabTestById(id)){
            throw new LabTestNotFoundException("LabTest doesn't exit");
        }
        labTestService.removeLabTestById(id);
        return ResponseEntity.ok("LabTest deleted successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LabTestDTO> updateLabTestById(@PathVariable("id") Long id,@RequestBody LabTestDTO labTestDTO){
        if(!labTestValidationService.isExitLabTestById(id)){
            throw new LabTestNotFoundException("LabTest doesn't exit");
        }
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(
                labTestService.updateLabTest(id,labTestDTO)
        ));
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<Map<String, List<LabTestDTO>>> fetchAllLabTests(){
        return ResponseEntity.ok(Map.of("labTests",labTestMapper.toLabTestDTOS(labTestService.getAllLabTest())));
    }

}
