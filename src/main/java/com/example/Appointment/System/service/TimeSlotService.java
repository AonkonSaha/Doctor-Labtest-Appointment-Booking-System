package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.TimeSlotDTO;
import org.springframework.stereotype.Service;


@Service
public interface TimeSlotService {

    TimeSlotDTO saveTimeSlot(TimeSlotDTO timeSlotDTO);
    boolean isExitTimeSlotById(Long id);
    TimeSlotDTO updateTimeSlotById(Long id, TimeSlotDTO timeSlotDTO);
    TimeSlotDTO getTimeSlot(Long i);
}
