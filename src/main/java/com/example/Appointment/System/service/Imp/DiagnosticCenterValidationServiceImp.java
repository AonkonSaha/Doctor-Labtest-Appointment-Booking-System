package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.repository.DiagnosticCenterRepo;
import com.example.Appointment.System.service.DiagnosticCenterValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosticCenterValidationServiceImp implements DiagnosticCenterValidationService {
    private final DiagnosticCenterRepo diagnosticCenterRepo;

    @Override
    public boolean isExitDianosticCenterById(Long id) {
        return diagnosticCenterRepo.existsById(id) ;
    }

    @Override
    public boolean isExitDiagnosticCenterName(String diagnosticCenterName) {
        return diagnosticCenterRepo.existsByDiagnosticCenterName(diagnosticCenterName) ;
    }

    @Override
    public boolean isEmptyCityName(String cityName) {
        return cityName.isEmpty();
    }

    @Override
    public boolean isEmptyCountryName(String countryName) {
        return countryName.isEmpty();
    }

    @Override
    public boolean isEmptyAddress(String address) {
        return address.isEmpty();
    }

    @Override
    public boolean isEmptyRoadName(String roadName) {
        return roadName.isEmpty();
    }

    @Override
    public boolean isEmptyHouseNumber(String houseNumber) {
        return houseNumber.isEmpty();
    }

    @Override
    public boolean isEmptyDiagnoseCenterName(String diagnoseCenterName) {
        return diagnoseCenterName.isEmpty() ;
    }

    @Override
    public boolean isEmptyContactNumber(String contactNumber) {
        return contactNumber.isEmpty();
    }

    @Override
    public boolean isEmptyEmail(String email) {
        return email.isEmpty() ;
    }
}
