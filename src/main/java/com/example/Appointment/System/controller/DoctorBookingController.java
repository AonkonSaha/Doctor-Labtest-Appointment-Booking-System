package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.exception.DoctorBookNotFoundException;
import com.example.Appointment.System.exception.DoctorNotFoundException;
import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.mapper.DoctorBookingMapper;
import com.example.Appointment.System.service.DoctorBookingService;
import com.example.Appointment.System.service.UserValidationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiPaths.DoctorBooking.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DoctorBookingController {
    private final DoctorBookingService doctorBookingService;
    private final DoctorBookingMapper doctorBookingMapper;
    private final UserValidationService userValidationService;
    @PostMapping(ApiPaths.DoctorBooking.REGISTER)
    public ResponseEntity<DoctorBookingDTO> registerDoctorBooking(@RequestBody DoctorBookingDTO doctorBookingDTO){
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(doctorBookingService.saveDoctorBooking(
                doctorBookingMapper.toDoctorBooking(doctorBookingDTO))));
    }
    @GetMapping(ApiPaths.DoctorBooking.FETCH_BY_ID)
    public ResponseEntity<DoctorBookingDTO> fetchDoctorBookingById(@PathVariable("id") Long id) {
        if(!doctorBookingService.isExitDoctorBookById(id)){
            throw new DoctorBookNotFoundException("Doctor booking doesn't exit");
        }
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(
                doctorBookingService.fetchDoctorBookById(id)));
    }
    @DeleteMapping(ApiPaths.DoctorBooking.DELETE)
    public ResponseEntity<String> deleteDoctorBookingById(@PathVariable("id") Long id)  {
        if(!doctorBookingService.isExitDoctorBookById(id)){
            throw new DoctorBookNotFoundException("DoctorBook doesn't exit");
        }
        doctorBookingService.deleteDoctorBookById(id);
        return ResponseEntity.ok("Doctor booking deleted successfully");
    }
    @PutMapping(ApiPaths.DoctorBooking.UPDATE)
    public ResponseEntity<DoctorBookingDTO> updateDoctorBookingById(
            @PathVariable("id") Long id,@RequestBody DoctorBookingDTO doctorBookingDTO){
        if(!doctorBookingService.isExitDoctorBookById(id)){
            throw new DoctorBookNotFoundException("DoctorBook doesn't exit");
        }
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(
                doctorBookingService.updateDoctorBooking(id,doctorBookingDTO)
        ));
    }
    @GetMapping(ApiPaths.DoctorBooking.FETCH_ALL)
    public ResponseEntity<List<DoctorBookingDTO>> fetchAllDoctorBookings(){
        if(doctorBookingService.fetchAllDoctorBooking().isEmpty()){
            throw new DoctorBookNotFoundException("DoctorBook doesn't exit");
        }
        return ResponseEntity.ok(
                doctorBookingMapper.toDoctorBookingDTOS(doctorBookingService.fetchAllDoctorBooking()));
    }
    @GetMapping(ApiPaths.DoctorBooking.TIME_SLOT)
    public ResponseEntity<Map<String,List<String>>> fetchTimeSlotDoctorBooking( @RequestParam Long doctorId,
                                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        if(!userValidationService.isExitUserById(doctorId)){
            throw new DoctorNotFoundException("Doctor doesn't exit");
        }
        if (date==null){
            throw new IllegalArgumentException("Date is null");
        }
        return ResponseEntity.ok(Map.of("bookedSlots", doctorBookingService.getTimeSlotDoctorBooking(doctorId, date)));
    }
    @GetMapping(ApiPaths.DoctorBooking.HISTORY)
    public ResponseEntity<Map<String,List<DoctorBookingDTO>>> fetchDoctorBookingHistoryByUser(){
        return ResponseEntity.ok(Map.of("doctorBookingHistories",doctorBookingMapper.toDoctorBookingDTOS(
                doctorBookingService.getDoctorBookingHistory()
        )));
    }
}
