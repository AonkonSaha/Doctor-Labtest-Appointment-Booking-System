package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
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

import static com.example.Appointment.System.constant.ApiPaths.LabTestBooking;

@RestController
@RequestMapping(LabTestBooking.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class LabTestBookingController {
    private final LabTestBookingService labTestBookingService;
    private final LabTestBookingMapper labTestBookingMapper;

    @PostMapping(LabTestBooking.REGISTER)
    public ResponseEntity<LabTestBookingDTO> registerLabTestBooking(@RequestBody LabTestBookingDTO labTestBookingDTO){
        return ResponseEntity.ok(labTestBookingMapper.toLabTestBookingDTO(
                labTestBookingService.saveLabTestBooking(labTestBookingMapper.toLabTestBooking(labTestBookingDTO))));
    }

    @GetMapping(LabTestBooking.FETCH_BY_ID)
    public ResponseEntity<LabTestBookingDTO> fetchLabTestBookingById(@PathVariable("id") Long id){
        if(!labTestBookingService.isExitLabTestBookingById(id)){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        return ResponseEntity.ok(
                labTestBookingMapper.toLabTestBookingDTO(labTestBookingService.getLabTestBookingById(id))
        );
    }

    @DeleteMapping(LabTestBooking.DELETE)
    public ResponseEntity<String> deleteLabTestBookingById(@PathVariable Long id){
        if(!labTestBookingService.isExitLabTestBookingById(id)){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        labTestBookingService.removeLabTestBookingById(id);
        return ResponseEntity.ok("LabTestBooking deleted successfully");
    }

    @PutMapping(LabTestBooking.UPDATE)
    public ResponseEntity<LabTestBookingDTO> updateLabTestBookingById(@PathVariable("id") Long id,@RequestBody LabTestBookingDTO labTestBookingDTO){
        if(!labTestBookingService.isExitLabTestBookingById(id)){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        return ResponseEntity.ok(labTestBookingMapper.toLabTestBookingDTO(
                labTestBookingService.modifyLabTestBookingById(id,labTestBookingDTO)));
    }

    @GetMapping(LabTestBooking.FETCH_ALL)
    public ResponseEntity<List<LabTestBookingDTO>> fetchAllLabTestBookings(){
        if(labTestBookingService.getAllLabTestBooking().isEmpty()){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        return ResponseEntity.ok(
                labTestBookingMapper.toLabTestBookingDTOS(labTestBookingService.getAllLabTestBooking())
        );
    }

    @GetMapping(LabTestBooking.HISTORY)
    public ResponseEntity<Map<String,List<LabTestBookingDTO>>> fetchAllLabTestBookingHistoryByUser(){
        return ResponseEntity.ok(Map.of("labTestsBook",labTestBookingMapper.toLabTestBookingDTOS(
                labTestBookingService.getAllLabTestBookHistoryByUser()
        )));
    }
}
