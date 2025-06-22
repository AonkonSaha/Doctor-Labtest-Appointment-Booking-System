package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.model.dto.TimeSlotDTO;
import com.example.Appointment.System.service.TimeSlotService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(ApiPaths.TimeSlot.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    @PostMapping(ApiPaths.TimeSlot.REGISTER)
    public ResponseEntity<TimeSlotDTO> registerTimeSlot(@RequestBody TimeSlotDTO timeSlotDTO){
        return ResponseEntity.ok(timeSlotService.saveTimeSlot(timeSlotDTO));
    }

    @PutMapping(ApiPaths.TimeSlot.UPDATE)
    public ResponseEntity<TimeSlotDTO> updateTimeSlot(@PathVariable("id") Long id, @RequestBody TimeSlotDTO timeSlotDTO){
        if(!timeSlotService.isExitTimeSlotById(id)){
            throw new IllegalArgumentException("Time Slot doesn't exist");
        }
        return ResponseEntity.ok(timeSlotService.updateTimeSlotById(id, timeSlotDTO));
    }

    @GetMapping(ApiPaths.TimeSlot.FETCH)
    public ResponseEntity<Map<String, TimeSlotDTO>> getTimeSlot(){
        long timeSlotId = 1;
        return ResponseEntity.ok(Map.of("timeSlots", timeSlotService.getTimeSlot(timeSlotId)));
    }
}
