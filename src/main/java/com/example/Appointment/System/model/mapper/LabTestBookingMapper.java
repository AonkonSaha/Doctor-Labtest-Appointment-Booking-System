package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.enums.Status;
import com.example.Appointment.System.exception.LabTestBookingNotFoundException;
import com.example.Appointment.System.exception.PatientNotFoundException;
import com.example.Appointment.System.model.dto.LabTestBookingDTO;
import com.example.Appointment.System.model.entity.DiagnosticCenter;
import com.example.Appointment.System.model.entity.LabTest;
import com.example.Appointment.System.model.entity.LabTestBooking;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.example.Appointment.System.repository.DiagnosticCenterRepo;
import com.example.Appointment.System.repository.LabTestBookingRepo;
import com.example.Appointment.System.repository.LabTestRepo;
import com.example.Appointment.System.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class LabTestBookingMapper {
    private final DiagnosticCenterRepo diagnosticCenterRepo;
    private final PatientRepo patientRepo;
    private final LabTestRepo labTestRepo;
    public LabTestBooking toLabTestBooking(LabTestBookingDTO labTestBookingDTO) throws LabTestBookingNotFoundException, PatientNotFoundException {
          Optional<PatientProfile> patient=patientRepo.findByPatientContact(SecurityContextHolder.getContext().getAuthentication().getName());
          Optional<DiagnosticCenter>diagnosticCenter=diagnosticCenterRepo.findById(labTestBookingDTO.getLabId());
          Optional<LabTest> labTest=labTestRepo.findById(labTestBookingDTO.getLabTestId());

        if(diagnosticCenter.isEmpty()){
            throw new LabTestBookingNotFoundException("LabTestBooking doesn't exit");
        }
        if(patient.isEmpty()){
            throw new PatientNotFoundException("Patient doesn't exit");
        }
        if(labTest.isEmpty()){
            throw new IllegalArgumentException("LabTest dose not exist");
        }

        LabTestBooking labTestBooking= LabTestBooking.builder()
                .labTestName(labTest.get().getLabTestName())
                .oderDate(labTestBookingDTO.getOderDate())
                .status(Status.Confirmed.name())
                .deliveryDate(labTestBookingDTO.getOderDate())
                .note(labTestBookingDTO.getNote())
                .build();
        patient.get().getLabTestBookings().add(labTestBooking);
        diagnosticCenter.get().getLabTestBookings().add(labTestBooking);
        labTestBooking.setPatientProfile(patient.get());
        labTestBooking.setDiagnosticCenter(diagnosticCenter.get());
        labTestBooking.setLabTest(labTest.get());
        return labTestBooking;
    }

    public LabTestBookingDTO toLabTestBookingDTO(LabTestBooking labTestBooking) {
        return LabTestBookingDTO.builder()
                .labTestName(labTestBooking.getLabTestName())
                .oderDate(labTestBooking.getOderDate())
                .deliveryDate(labTestBooking.getDeliveryDate())
                .note(labTestBooking.getNote())
                .id(labTestBooking.getId())
                .labId(labTestBooking.getDiagnosticCenter().getId())
                .labTestId(labTestBooking.getLabTest().getId())
                .diagnosticCenterName(labTestBooking.getDiagnosticCenter().getDiagnosticCenterName())
                .status(labTestBooking.getStatus())
                .labTestImageUrl(labTestBooking.getLabTest().getLabTestImageUrl())
                .build();
    }

    public List<LabTestBookingDTO> toLabTestBookingDTOS(List<LabTestBooking> allLabTestBooking) {
        List<LabTestBookingDTO>labTestBookingDTOS=new ArrayList<>();
        for (LabTestBooking labTestBooking:allLabTestBooking){
            labTestBookingDTOS.add(toLabTestBookingDTO(labTestBooking));
        }
        return labTestBookingDTOS;
    }
}
