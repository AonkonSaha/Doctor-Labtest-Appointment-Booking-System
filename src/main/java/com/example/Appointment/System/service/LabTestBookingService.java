package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.LabTestBookingDTO;
import com.example.Appointment.System.model.entity.LabTestBooking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabTestBookingService{

    LabTestBooking saveLabTestBooking(LabTestBooking labTestBooking);
    boolean isExitLabTestBookingById(Long id);
    LabTestBooking getLabTestBookingById(Long id);
    void removeLabTestBookingById(Long id);
    LabTestBooking modifyLabTestBookingById(Long id, LabTestBookingDTO labTestBookingDTO);
    List<LabTestBooking> getAllLabTestBooking();
    List<LabTestBooking> getAllLabTestBookHistoryByUser();

}
