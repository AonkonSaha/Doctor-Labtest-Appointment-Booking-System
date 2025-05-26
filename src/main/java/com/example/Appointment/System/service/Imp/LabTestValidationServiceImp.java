package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.repository.LabTestRepo;
import com.example.Appointment.System.service.LabTestValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabTestValidationServiceImp implements LabTestValidationService {
    private final LabTestRepo labTestRepo;
    @Override
    public boolean isEmptyLabTestName(String labTestName) {
        return labTestName.isEmpty();
    }
    @Override
    public boolean isEmptyLabTestDescription(String labTestDescription) {
        return labTestDescription.isEmpty();
    }
    @Override
    public boolean isExitLabTestName(String labTestName) {
        return labTestRepo.existsByLabTestName(labTestName);
    }

    @Override
    public boolean isExitLabTestById(Long id) {
        return labTestRepo.existsById(id);
    }
}
