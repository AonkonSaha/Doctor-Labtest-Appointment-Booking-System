package com.example.Appointment.System.service;

public interface DiagnosticCenterValidationService {

    boolean isExitDianosticCenterById(Long id);
    boolean isExitDiagnosticCenterName(String diagnosticCenterName);
    boolean isEmptyCityName(String cityName);
    boolean isEmptyCountryName(String countryName);
    boolean isEmptyAddress(String address);
    boolean isEmptyRoadName(String roadName);
    boolean isEmptyHouseNumber(String houseNumber);
    boolean isEmptyDiagnoseCenterName(String diagnoseCenterName);
    boolean isEmptyContactNumber(String contactNumber);
    boolean isEmptyEmail(String email);
}
