package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.InvalidLabTestArgumentException;
import com.example.Appointment.System.exception.LabTestNotFoundException;
import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.mapper.LabTestMapper;
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
@RequestMapping(ApiPaths.LabTest.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LabTestController {
    private final LabTestService labTestService;
    private final LabTestMapper labTestMapper;
    private final ValidationService validationService;
    private final LabTestValidationService labTestValidationService;

    @PostMapping(ApiPaths.LabTest.REGISTER)
    public ResponseEntity<LabTestDTO> registerLabTest(@RequestBody LabTestDTO labTestDTO){
        if(!validationService.validateLabTestDetails(labTestDTO).isEmpty()){
            throw new InvalidLabTestArgumentException(validationService.validateLabTestDetails(labTestDTO));
        }
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(
                labTestService.saveLabTest(labTestMapper.toLabTest(labTestDTO))));
    }

    @GetMapping(ApiPaths.LabTest.FETCH_BY_ID)
    public ResponseEntity<LabTestDTO> fetchLabTestById(@PathVariable("id") Long id){
        if(!labTestValidationService.isExitLabTestById(id)){
            throw new LabTestNotFoundException("LabTest doesn't exit");
        }
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(labTestService.getLabTestById(id)));
    }

    @DeleteMapping(ApiPaths.LabTest.DELETE)
    public ResponseEntity<String> deleteLabTestById(@PathVariable("id") Long id){
        if(!labTestValidationService.isExitLabTestById(id)){
            throw new LabTestNotFoundException("LabTest doesn't exit");
        }
        labTestService.removeLabTestById(id);
        return ResponseEntity.ok("LabTest deleted successfully");
    }

    @PutMapping(ApiPaths.LabTest.UPDATE)
    public ResponseEntity<LabTestDTO> updateLabTestById(@PathVariable("id") Long id, @RequestBody LabTestDTO labTestDTO){
        if(!labTestValidationService.isExitLabTestById(id)){
            throw new LabTestNotFoundException("LabTest doesn't exit");
        }
        return ResponseEntity.ok(labTestMapper.toLabTestDTO(labTestService.updateLabTest(id, labTestDTO)));
    }

    @GetMapping(ApiPaths.LabTest.FETCH_ALL)
    public ResponseEntity<Map<String, List<LabTestDTO>>> fetchAllLabTests(){
        if(labTestService.getAllLabTest().isEmpty()){
            throw new LabTestNotFoundException("LabTest doesn't exit");
        }
        return ResponseEntity.ok(Map.of("labTests", labTestMapper.toLabTestDTOS(labTestService.getAllLabTest())));
    }
}
