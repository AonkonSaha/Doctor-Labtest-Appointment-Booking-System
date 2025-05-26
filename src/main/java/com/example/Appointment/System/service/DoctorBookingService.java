package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.entity.DoctorBooking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface DoctorBookingService {

     DoctorBooking saveDoctorBooking(DoctorBooking doctorBooking);
     boolean isExitDoctorBookById(Long id);
     DoctorBooking fetchDoctorBookById(Long id);
     void deleteDoctorBookById(Long id);
     DoctorBooking updateDoctorBooking(Long id, DoctorBookingDTO doctorBookingDTO);
     List<DoctorBooking> fetchAllDoctorBooking();
     List<String> getTimeSlotDoctorBooking(Long doctorId, LocalDate date);
     List<DoctorBooking> getDoctorBookingHistory();


}
