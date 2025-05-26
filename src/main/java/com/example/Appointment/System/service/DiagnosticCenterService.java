package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.entity.DiagnosticCenter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiagnosticCenterService {

     DiagnosticCenter saveDiagnosticCenter(DiagnosticCenter diagnosticCenter);
     DiagnosticCenter getDiagnosticCenterById(Long id);
     void removeDiagnosticCenter(Long id);
     DiagnosticCenter updateDiagnosticCenter(Long id, DiagnosticDTO diagnosticDTO);
     List<DiagnosticCenter> getAllDiagnosticCenter();
     boolean isExitDianosticCenterName(String diagnosticCenterName);

}
