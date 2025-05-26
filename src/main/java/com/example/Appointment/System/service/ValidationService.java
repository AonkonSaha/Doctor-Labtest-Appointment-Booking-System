package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.*;
import org.springframework.stereotype.Service;


@Service
public interface ValidationService {

    String validatePatientDetails(UserDTO userDTO);
    String validateDoctorDetails(DoctorDTO doctorDTO);
    String validateLabTestDetails(LabTestDTO labTestDTO);
    String validateLabTestBookingDetails(LabTestBookingDTO labTestBookingDTO);
    String validateDoctorBookingDetails(DoctorBookingDTO doctorBookingDTO);
    String validatePatientPasswordUpdate(PasswordDTO passwordDTO);
    String validateDiagnosisCenterDetails(DiagnosticDTO diagnosticDTO);
}
