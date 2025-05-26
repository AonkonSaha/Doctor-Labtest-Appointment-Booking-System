package com.example.Appointment.System.service;

public interface LabTestValidationService {

    boolean isEmptyLabTestName(String labTestName);
    boolean isEmptyLabTestDescription(String labTestDescription);
    boolean isExitLabTestName(String labTestName);
    boolean isExitLabTestById(Long id);
}
