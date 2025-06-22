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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPaths.Doctor.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final UserServiceImp userServiceImp;
    private final UserValidationService userValidationService;
    private final ValidationService validationService;

    @PostMapping(ApiPaths.Doctor.REGISTER)
    public ResponseEntity<DoctorDTO> registerDoctor(@RequestBody DoctorDTO doctorDTO){
        if(!validationService.validateDoctorDetails(doctorDTO).isEmpty()){
            throw new InvalidDoctorArgumentException(validationService.validateDoctorDetails(doctorDTO));
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.saveDoctor(doctorMapper.toDoctor(doctorDTO))
        ));}
    @GetMapping(ApiPaths.Doctor.FETCH_BY_ID)
    public ResponseEntity<DoctorDTO>fetchDoctorById(@PathVariable("id") Long id) throws DoctorNotFoundException {
        if(!userValidationService.isExitUserById(id)){
            throw new DoctorNotFoundException("Doctor doesn't exit");
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.getDoctorById(id)));
    }
    @PutMapping(ApiPaths.Doctor.UPDATE)
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<DoctorDTO> updateDoctorById(@PathVariable("id") Long id,@RequestBody DoctorDTO doctorDTO){
        if(!userValidationService.isExitUserById(id)){
            throw new DoctorNotFoundException("Doctor doesn't exit");
        }
        if(!validationService.validateDoctorDetails(doctorDTO).isEmpty()){
            throw new InvalidDoctorArgumentException(validationService.validateDoctorDetails(doctorDTO));
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.updateDoctorById(id,doctorDTO)));
    }
    @DeleteMapping(ApiPaths.Doctor.DELETE)
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> deleteDoctorById(@PathVariable("id") Long id){
        if(!userValidationService.isExitUserById(id)){
            throw new DoctorNotFoundException("Doctor doesn't exit");
        }
        doctorService.deleteDoctorByDoctorId(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
    @GetMapping(ApiPaths.Doctor.FETCH_ALL)
    public ResponseEntity<Map<String,List<DoctorDTO>>> fetchAllDoctors(){
        System.out.println("---------------------------------AXSX-----------------");
        if(doctorService.getAllDoctor().isEmpty()){
            throw new DoctorNotFoundException("Doctor doesn't exit");
        }
        List<DoctorDTO>doctorDTOS=doctorMapper.toDoctorDTOs(doctorService.getAllDoctor());
        return ResponseEntity.ok(Map.of(
                "doctors",doctorDTOS
        ));
    }

}
