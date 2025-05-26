package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.entity.DoctorProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {

    DoctorProfile saveDoctor(DoctorProfile doctorProfile);
    DoctorProfile updateDoctorById(Long id, DoctorDTO doctorDTO);
    DoctorProfile getDoctorById(Long id);
    void deleteDoctorByDoctorId(Long id);
    List<DoctorProfile> getAllDoctor();

}
