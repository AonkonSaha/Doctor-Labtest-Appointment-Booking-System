package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.entity.PatientProfile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientMapper {

    public PatientProfile toPatient(PatientDTO patientDTO) {
        return PatientProfile.builder()
                .dateOfBirth(patientDTO.getDateOfBirth())
                .build();
    }

    public PatientDTO toPatientDTO(PatientProfile patientProfile) {
        return PatientDTO.builder()
                .dateOfBirth(patientProfile.getDateOfBirth())
                .build();
    }

    public List<PatientDTO> toPatientDTOS(List<PatientProfile> allPatientProfile) {
        List<PatientDTO>patientDTOS=new ArrayList<>();
        for (PatientProfile patientProfile : allPatientProfile){
            patientDTOS.add(toPatientDTO(patientProfile));
        }
        return patientDTOS;
    }
}
