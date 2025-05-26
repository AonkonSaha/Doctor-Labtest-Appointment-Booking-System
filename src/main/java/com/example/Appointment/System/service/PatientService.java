package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.entity.PatientProfile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PatientService {

    PatientProfile savePatient(PatientProfile patientProfile);
    boolean isExitPatientById(Long id);
    PatientProfile getPatientById(Long id);
    void deletePatientByPatientId(Long id);
    PatientProfile updatePatientByPatientId(Long id, PatientDTO patientDTO);
    List<PatientProfile> getAllPatient();
    PatientProfile getPatientByName(String name);

}
