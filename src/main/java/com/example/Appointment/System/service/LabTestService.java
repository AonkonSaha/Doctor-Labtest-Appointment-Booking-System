package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.entity.LabTest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LabTestService {
    LabTest saveLabTest(LabTest labTest);
    LabTest getLabTestById(Long id);
    void removeLabTestById(Long id);
    LabTest updateLabTest(Long id, LabTestDTO labTestDTO);
    List<LabTest> getAllLabTest();
}
