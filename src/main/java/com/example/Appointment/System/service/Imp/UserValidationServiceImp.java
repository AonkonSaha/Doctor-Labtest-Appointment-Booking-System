package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.repository.UserRepo;
import com.example.Appointment.System.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserValidationServiceImp implements UserValidationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isEmptyUserName(String name) {
        return name.isEmpty();
    }

    @Override
    public boolean isExitUserByContact(String contact) {
        return userRepo.existsByContact(contact);
    }

    @Override
    public boolean isEmptyUserContact(String contact) {
        return contact.isEmpty();
    }

    @Override
    public boolean isValidUserContactLength(String contact) {
        return contact.length() == 11;
    }

    @Override
    public boolean isValidUserPasswordLength(String password) {
        return password.length() >= 8;
    }

    @Override
    public boolean isValidEmailFormat(String email) {
        return email.contains("@");
    }

    @Override
    public boolean isValidGender(String gender) {
        Set<String> genders=Set.of("male","female","other");
        return genders.contains(gender.toLowerCase());
    }
    @Override
    public boolean isExitUserById(Long id) {
        return userRepo.existsById(id);
    }

    @Override
    public boolean isExitUserPassword(String contact,String password) {
        Optional<MUser> mUser=userRepo.findByContact(contact);
        if(mUser.isEmpty()){
            return false;
        }
        return passwordEncoder.matches(password,mUser.get().getPassword());
    }
    @Override
    public boolean isEqualNewPassAndConfirmPass(String newPass, String confirmPass){
        return newPass.equals(confirmPass) ;
    }

    @Override
    public boolean isEmptyDoctorDesignation(String designation) {
        return designation.isEmpty();
    }

    @Override
    public boolean isEmptyDoctorDegrees(Set<String> degrees) {
        return degrees.isEmpty();
    }


}
