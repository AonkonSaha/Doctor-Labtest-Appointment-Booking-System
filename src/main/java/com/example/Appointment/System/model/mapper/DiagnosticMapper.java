package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.entity.DiagnosticCenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DiagnosticMapper {

    public DiagnosticCenter toDiagnosticCenter(DiagnosticDTO diagnosticDTO) {

        return DiagnosticCenter.builder()
                .diagnosticCenterName(diagnosticDTO.getDiagnosticCenterName())
                .email(diagnosticDTO.getEmail())
                .city(diagnosticDTO.getCity())
                .country(diagnosticDTO.getCountry())
                .address(diagnosticDTO.getAddress())
                .roadNo(diagnosticDTO.getRoadNo())
                .holdingNo(diagnosticDTO.getHoldingNo())
                .operatingHours(diagnosticDTO.getOperatingHours())
                .contactNumber(diagnosticDTO.getContactNumber())
                .zipCode(diagnosticDTO.getZipCode())
                .build();

    }
    public  DiagnosticDTO toDiagnosticDTO(DiagnosticCenter diagnosticCenter) {
        return DiagnosticDTO.builder()
                .labId(diagnosticCenter.getId())
                .diagnosticCenterName(diagnosticCenter.getDiagnosticCenterName())
                .contactNumber(diagnosticCenter.getContactNumber())
                .operatingHours(diagnosticCenter.getOperatingHours())
                .zipCode(diagnosticCenter.getZipCode())
                .roadNo(diagnosticCenter.getRoadNo())
                .holdingNo(diagnosticCenter.getHoldingNo())
                .address(diagnosticCenter.getAddress())
                .country(diagnosticCenter.getCountry())
                .city(diagnosticCenter.getCity())
                .email(diagnosticCenter.getEmail())
                .build();
    }

    public List<DiagnosticDTO> toDiagnosticDTOS(List<DiagnosticCenter> allDiagnosticCenter) {
        List<DiagnosticDTO>diagnosticDTOS=new ArrayList<>();
        for (DiagnosticCenter diagnosticCenter :allDiagnosticCenter){
            diagnosticDTOS.add(toDiagnosticDTO(diagnosticCenter));
        }
        return diagnosticDTOS;
    }
}
