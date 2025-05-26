package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.LabTestBookingNotFoundException;
import com.example.Appointment.System.exception.PatientNotFoundException;
import com.example.Appointment.System.model.dto.LabTestBookingDTO;
import com.example.Appointment.System.model.mapper.LabTestBookingMapper;
import com.example.Appointment.System.service.Imp.LabTestBookingServiceImp;
import com.example.Appointment.System.service.LabTestBookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/booking/lab/test")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LabTestBookingController {
    private final LabTestBookingService labTestBookingService;
    private final LabTestBookingMapper labTestBookingMapper;
    @GetMapping("/test")
    public ResponseEntity<String> test()  {
        return ResponseEntity.ok("End Point Test");
    }
    @PostMapping("/register")
    public ResponseEntity<LabTestBookingDTO> registerLabTestBooking(@RequestBody LabTestBookingDTO labTestBookingDTO) throws PatientNotFoundException, LabTestBookingNotFoundException {
        return ResponseEntity.ok(labTestBookingMapper.toLabTestBookingDTO(
                labTestBookingService.saveLabTestBooking(labTestBookingMapper.toLabTestBooking(labTestBookingDTO))));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<LabTestBookingDTO> fetchLabTestBookingById(@PathVariable("id") Long id) throws LabTestBookingNotFoundException {
        if(!labTestBookingService.isExitLabTestBookingById(id)){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        return ResponseEntity.ok(
                labTestBookingMapper.toLabTestBookingDTO(labTestBookingService.getLabTestBookingById(id))
        );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLabTestBookingById(@PathVariable Long id) throws LabTestBookingNotFoundException {
        if(!labTestBookingService.isExitLabTestBookingById(id)){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        labTestBookingService.removeLabTestBookingById(id);
        return ResponseEntity.ok("LabTestBooking deleted successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LabTestBookingDTO> updateLabTestBookingById(@PathVariable("id") Long id,@RequestBody LabTestBookingDTO labTestBookingDTO) throws LabTestBookingNotFoundException {
        if(!labTestBookingService.isExitLabTestBookingById(id)){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        return ResponseEntity.ok(labTestBookingMapper.toLabTestBookingDTO(
                labTestBookingService.modifyLabTestBookingById(id,labTestBookingDTO)));
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<List<LabTestBookingDTO>> fetchAllLabTestBookings() throws LabTestBookingNotFoundException {
        if(labTestBookingService.getAllLabTestBooking().isEmpty()){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        return ResponseEntity.ok(
                labTestBookingMapper.toLabTestBookingDTOS(labTestBookingService.getAllLabTestBooking())
        );
    }

    @GetMapping("/fetch/all/history")
    public ResponseEntity<Map<String,List<LabTestBookingDTO>>> fetchAllLabTestBookingHistoryByUser(){
        return ResponseEntity.ok(Map.of("labTestsBook",labTestBookingMapper.toLabTestBookingDTOS(
                labTestBookingService.getAllLabTestBookHistoryByUser()
        )));
    }
}
