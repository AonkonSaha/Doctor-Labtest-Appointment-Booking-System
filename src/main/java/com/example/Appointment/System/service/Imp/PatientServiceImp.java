package com.example.Appointment.System.service.Imp;


import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.example.Appointment.System.repository.PatientRepo;
import com.example.Appointment.System.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {
    private final PatientRepo patientRepo;

    @Override
    public PatientProfile savePatient(PatientProfile patientProfile) {
        patientRepo.save(patientProfile);
        return patientProfile;
    }

    @Override
    public boolean isExitPatientById(Long id) {
        return patientRepo.existsById(id);
    }

    @Override
    public PatientProfile getPatientById(Long id) {
         Optional<PatientProfile> patient=patientRepo.findById(id);
         if(patient.isEmpty()){
             return null;
         }
         return patient.get();
    }

    @Override
    public void deletePatientByPatientId(Long id) {
        patientRepo.deleteById(id);
    }

    @Override
    public PatientProfile updatePatientByPatientId(Long id, PatientDTO patientDTO) {
        Optional<PatientProfile> patient=patientRepo.findById(id);
        if(patient.isEmpty()){
            return null;
        }
        patient.get().setDateOfBirth(patientDTO.getDateOfBirth());
        patientRepo.save(patient.get());
        return patient.get();
    }

    @Override
    public List<PatientProfile> getAllPatient() {
        List<PatientProfile> patientProfiles = patientRepo.findAll();
        if(patientProfiles.isEmpty()){
            return new ArrayList<>();
        }
        return patientRepo.findAll();
    }

    @Override
    public PatientProfile getPatientByName(String name) {
        Optional<PatientProfile> patient=patientRepo.findByPatientName(name);
        if(patient.isEmpty()){
            return null;
        }
        return patient.get();
    }


}
