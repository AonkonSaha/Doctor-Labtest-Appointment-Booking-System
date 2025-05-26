package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.model.dto.TimeSlotDTO;
import com.example.Appointment.System.model.entity.TimeSlot;
import com.example.Appointment.System.repository.TimeSlotRepo;
import com.example.Appointment.System.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImp  implements TimeSlotService {
    private final TimeSlotRepo timeSlotRepo;

    @Override
    public TimeSlotDTO saveTimeSlot(TimeSlotDTO timeSlotDTO) {
        TimeSlot timeSlot = TimeSlot.builder()
                .numberOfSlots(timeSlotDTO.getNumberOfSlots())
                .durable(timeSlotDTO.getDurable())
                .startHour(timeSlotDTO.getStartHour())
                .build();
        timeSlotRepo.save(timeSlot);
        return timeSlotDTO;
    }

    @Override
    public boolean isExitTimeSlotById(Long id) {
        return timeSlotRepo.existsById(id);
    }

    @Override
    public TimeSlotDTO updateTimeSlotById(Long id, TimeSlotDTO timeSlotDTO) {
        if(!isExitTimeSlotById(id)){
            throw new IllegalArgumentException("Time Slot doesn't exit");
        }
        TimeSlot timeSlot=timeSlotRepo.findById(id).get();
        timeSlot.setNumberOfSlots(timeSlotDTO.getNumberOfSlots());
        timeSlot.setDurable(timeSlotDTO.getDurable());
        timeSlot.setStartHour(timeSlotDTO.getStartHour());
        timeSlotRepo.save(timeSlot);
        return timeSlotDTO;
    }

    @Override
    public TimeSlotDTO getTimeSlot(Long i) {
        if(!isExitTimeSlotById(i)){
            throw new IllegalArgumentException("Time Slot doesn't exit");
        }
        TimeSlot timeSlot=timeSlotRepo.findById(i).get();
        return TimeSlotDTO.builder()
                .numberOfSlots(timeSlot.getNumberOfSlots())
                .durable(timeSlot.getDurable())
                .startHour(timeSlot.getStartHour())
                .build();

    }
}
