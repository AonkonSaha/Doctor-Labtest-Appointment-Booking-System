package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.example.Appointment.System.model.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public MUser toUser(UserDTO userDTO) {
        MUser user= MUser.builder()
                .email(userDTO.getEmail())
                .dateOfBirth(userDTO.getDateOfBirth())
                .gender(userDTO.getGender())
                .contact(userDTO.getContact())
                .name(userDTO.getName())
                .isActive(false)
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        UserRole userRole=new UserRole();
        userRole.setRole("PATIENT");
        userRole.setUsers(Set.of(user));
        user.setUserRoles(Set.of(userRole));
        PatientProfile patientProfile=new PatientProfile();
        patientProfile.setDateOfBirth(user.getDateOfBirth());
        patientProfile.setPatientName(user.getName());
        patientProfile.setUser(user);
        user.setPatientProfile(patientProfile);
        return user;
    }

    public UserDTO toUserDTO(MUser mUser) {
        return UserDTO.builder()
                .dateOfBirth(mUser.getDateOfBirth())
                .email(mUser.getEmail())
                .name(mUser.getName())
                .contact(mUser.getContact())
                .gender(mUser.getGender())
                .profilePictureUrl(mUser.getPatientProfile().getProfilePictureUrl())
                .build();
    }


}
