package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.model.dto.*;
import com.example.Appointment.System.service.DiagnosticCenterValidationService;
import com.example.Appointment.System.service.LabTestValidationService;
import com.example.Appointment.System.service.UserValidationService;
import com.example.Appointment.System.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
@RequiredArgsConstructor
public class ValidationServiceImp implements ValidationService {
    private final UserValidationService userValidationService;
    private final DiagnosticCenterValidationService diagnosticCenterValidationService;
    private final LabTestValidationService labTestValidationService;

    @Override
    public String validatePatientDetails(UserDTO userDTO) {

        if(userValidationService.isExitUserByContact(userDTO.getContact())){
            return "This Contact Number is already exit!";
        }
        if(userValidationService.isEmptyUserContact(userDTO.getContact())){
            return "Mobile Number can't be empty";
        }
        if(!userValidationService.isValidUserContactLength(userDTO.getContact())){
            return "Mobile Number must be 11 digits";
        }
        if(!userValidationService.isValidUserPasswordLength(userDTO.getPassword())){
            return "Password must be at least 8 characters long";
        }
        if(!userValidationService.isValidEmailFormat(userDTO.getEmail())){
            return "Email must contain @";
        }
        if(!userValidationService.isValidGender(userDTO.getGender())){
            return "Gender must be male,female or other";
        }
        return "";
    }

    @Override
    public String validateDoctorDetails(DoctorDTO doctorDTO) {
        if(userValidationService.isEmptyUserName(doctorDTO.getDoctorName())){
            return "Doctor Name can't be empty";
        }
        if(userValidationService.isExitUserByContact(doctorDTO.getContactNumber())){
            return "This Contact Number is already exit!";
        }
        if(userValidationService.isEmptyUserContact(doctorDTO.getContactNumber())){
            return "Mobile Number can't be empty";
        }
        if(!userValidationService.isValidUserContactLength(doctorDTO.getContactNumber())){
            return "Mobile Number must be 11 digits";
        }
        if(!userValidationService.isValidUserPasswordLength(doctorDTO.getPassword())){
            return "Password must be at least 8 characters long";
        }
        if(!userValidationService.isValidEmailFormat(doctorDTO.getEmail())){
            return "Email must contain @";
        }
        if(!userValidationService.isValidGender(doctorDTO.getGender())){
            return "Gender must be male,female or other";
        }
        if(userValidationService.isEmptyDoctorDesignation(doctorDTO.getDesignation())){
            return "Designation can't be empty";
        }
        if(userValidationService.isEmptyDoctorDegrees(doctorDTO.getDegrees())){
            return "Degrees can't be empty";
        }

        return "";
    }

    @Override
    public String validateLabTestDetails(LabTestDTO labTestDTO) {
        if(labTestValidationService.isEmptyLabTestName(labTestDTO.getLabTestName())){
            return "Lab Test Name can't be empty";
        }
        if (labTestValidationService.isExitLabTestName(labTestDTO.getLabTestName())){
            return "This Lab Test Name is already exit!";
        }

        return "";
    }

    @Override
    public String validateLabTestBookingDetails(LabTestBookingDTO labTestBookingDTO) {
        return "";
    }

    @Override
    public String validateDoctorBookingDetails(DoctorBookingDTO doctorBookingDTO) {
        return "";
    }

    @Override
    public String validatePatientPasswordUpdate(PasswordDTO passwordDTO) {
        String contact= SecurityContextHolder.getContext().getAuthentication().getName();
        if(!userValidationService.isEqualNewPassAndConfirmPass(passwordDTO.getNewPassword(),passwordDTO.getConfirmPassword())){
           return "New and confirm password must be the same";
        }
        if(!userValidationService.isExitUserPassword(contact,passwordDTO.getPassword())){
            return "Current Password is incorrect";
        }
        if(!userValidationService.isValidUserPasswordLength(passwordDTO.getNewPassword())){
            return "Password must be at least 8 characters long";
        }
        return "";
    }

    @Override
    public String validateDiagnosisCenterDetails(DiagnosticDTO diagnosticDTO) {
        if(diagnosticCenterValidationService.isExitDiagnosticCenterName(diagnosticDTO.getDiagnosticCenterName())){
            return "This Diagnostic Center Name is already exit!";
        }
        if (diagnosticCenterValidationService.isEmptyAddress(diagnosticDTO.getAddress())){
            return "Address can't be empty";
        }
        if (diagnosticCenterValidationService.isEmptyCityName(diagnosticDTO.getCity())){
            return "City can't be empty";
        }
        if (diagnosticCenterValidationService.isEmptyCountryName(diagnosticDTO.getCountry())){
            return "Country can't be empty";
        }
        if (diagnosticCenterValidationService.isEmptyContactNumber(diagnosticDTO.getContactNumber())){
            return "Contact Number can't be empty";
        }
        if (diagnosticCenterValidationService.isEmptyEmail(diagnosticDTO.getEmail())){
            return "Email can't be empty";
        }
        if (diagnosticCenterValidationService.isEmptyHouseNumber(diagnosticDTO.getHoldingNo())){
            return "Holding Number can't be empty";
        }
        if (diagnosticCenterValidationService.isEmptyRoadName(diagnosticDTO.getRoadNo())){
            return "Road Number can't be empty";
        }
        if (diagnosticCenterValidationService.isEmptyDiagnoseCenterName(diagnosticDTO.getDiagnosticCenterName())){
            return "Diagnose Center Name can't be empty";
        }
        return "";
    }
}
