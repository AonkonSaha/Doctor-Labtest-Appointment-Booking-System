package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public interface UserValidationService {

    boolean isEmptyUserName(String name);
    boolean isExitUserByContact(String contact);
    boolean isEmptyUserContact(String contact);
    boolean isValidUserContactLength(String contact);
    boolean isValidUserPasswordLength(String password);
    boolean isValidEmailFormat(String email);
    boolean isValidGender(String gender);
    boolean isExitUserById(Long id);
    boolean isExitUserPassword(String contact,String password);
    boolean isEqualNewPassAndConfirmPass(String newPass, String confirmPass);
    boolean isEmptyDoctorDesignation(String designation);
    boolean isEmptyDoctorDegrees(Set<String> degrees);
}
