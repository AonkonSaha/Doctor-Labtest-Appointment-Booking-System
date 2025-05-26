package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.PatientNotFoundException;
import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.mapper.PatientMapper;
import com.example.Appointment.System.service.Imp.PatientServiceImp;
import com.example.Appointment.System.service.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PatientController {
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    @PostMapping("/register")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO){
        return ResponseEntity.ok(patientMapper.toPatientDTO(
                patientService.savePatient(patientMapper.toPatient(patientDTO))));
    }
   @GetMapping("/fetch/{id}")
   public ResponseEntity<PatientDTO> fetchPatientById(@PathVariable("id") Long id) throws PatientNotFoundException {
       if(!patientService.isExitPatientById(id)){
            throw new PatientNotFoundException("Patient doesn't exit");
       }
       return ResponseEntity.ok(patientMapper.toPatientDTO(
               patientService.getPatientById(id)));
   }
   @PostMapping("/delete/{id}")
   public ResponseEntity<String> deletePatientById(@PathVariable("id") Long id) throws PatientNotFoundException {
       if(!patientService.isExitPatientById(id)){
           throw new PatientNotFoundException("Patient doesn't exit");
       }
       patientService.deletePatientByPatientId(id);
       return ResponseEntity.ok("Patient deleted successfully");
   }
   @PutMapping("/update/{id}")
   public ResponseEntity<PatientDTO> updatePatientById(@PathVariable("id") Long id,PatientDTO patientDTO) throws PatientNotFoundException {
        if(!patientService.isExitPatientById(id)){
            throw new PatientNotFoundException("Patient doesn't exit");
        }
        return ResponseEntity.ok(patientMapper.toPatientDTO(
                patientService.updatePatientByPatientId(id,patientDTO)));
   }
   @GetMapping("fetch/all")
   public ResponseEntity<List<PatientDTO>> fetchAllPatients() throws PatientNotFoundException {
        if(patientService.getAllPatient().isEmpty()){
            throw new PatientNotFoundException("Patient doesn't exit");
        }
        return ResponseEntity.ok(patientMapper.toPatientDTOS(patientService.getAllPatient()));
   }
}
