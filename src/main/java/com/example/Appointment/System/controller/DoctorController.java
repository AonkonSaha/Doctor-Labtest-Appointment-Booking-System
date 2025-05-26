package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.DoctorNotFoundException;
import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.mapper.DoctorMapper;
import com.example.Appointment.System.service.DoctorService;
import com.example.Appointment.System.service.Imp.DoctorServiceImp;
import com.example.Appointment.System.service.Imp.UserServiceImp;
import com.example.Appointment.System.service.UserValidationService;
import com.example.Appointment.System.service.ValidationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final UserServiceImp userServiceImp;
    private final UserValidationService userValidationService;
    private final ValidationService validationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDTO doctorDTO){
        if(!validationService.validateDoctorDetails(doctorDTO).isEmpty()){
            return ResponseEntity.badRequest().body(validationService.validateDoctorDetails(doctorDTO));
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.saveDoctor(doctorMapper.toDoctor(doctorDTO))
        ));}
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?>fetchDoctorById(@PathVariable("id") Long id) throws DoctorNotFoundException {
        if(!userValidationService.isExitUserById(id)){
            return ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.getDoctorById(id)));
    }
    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateDoctorById(@PathVariable("id") Long id,@RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException{
        if(!userValidationService.isExitUserById(id)){
            return ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.updateDoctorById(id,doctorDTO)));
    }
    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> deleteDoctorById(@PathVariable("id") Long id) throws DoctorNotFoundException{
        if(!userValidationService.isExitUserById(id)){
            return ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        doctorService.deleteDoctorByDoctorId(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<?> fetchAllDoctors() throws DoctorNotFoundException {
        if(doctorService.getAllDoctor().isEmpty()){
            return  ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        List<DoctorDTO>doctorDTOS=doctorMapper.toDoctorDTOs(doctorService.getAllDoctor());
        return ResponseEntity.ok(Map.of(
                "doctors",doctorDTOS
        ));
    }

}
