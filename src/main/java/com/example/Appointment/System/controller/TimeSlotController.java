package com.example.Appointment.System.controller;

import com.example.Appointment.System.model.dto.TimeSlotDTO;
import com.example.Appointment.System.service.Imp.TimeSlotServiceImp;
import com.example.Appointment.System.service.TimeSlotService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/time/slot")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @PostMapping("/register")
    public ResponseEntity<TimeSlotDTO> registerTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO){
        return ResponseEntity.ok(timeSlotService.saveTimeSlot(timeSlotDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TimeSlotDTO> updateTimeSlot(@PathVariable("id") Long id,@RequestBody TimeSlotDTO timeSlotDTO){
        if(!timeSlotService.isExitTimeSlotById(id)){
            throw new IllegalArgumentException("Time Slot doesn't exit");
        }
        return ResponseEntity.ok(
                timeSlotService.updateTimeSlotById(id,timeSlotDTO)
        );

    }
    @GetMapping("/fetch")
    public ResponseEntity<Map<String,TimeSlotDTO>> getTimeSlot(){
        long timeSlotId=1;
        return ResponseEntity.ok(
                Map.of("timeSlots", timeSlotService.getTimeSlot(timeSlotId)
        ));

    }

}
