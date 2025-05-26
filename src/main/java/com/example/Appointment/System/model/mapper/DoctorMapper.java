package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.entity.DoctorProfile;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.UserRole;
import com.example.Appointment.System.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DoctorMapper {
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepo doctorRepo;
    public DoctorProfile toDoctor(DoctorDTO doctorDTO) {
        DoctorProfile doctorProfile= DoctorProfile.builder()
                .languagesSpoken(doctorDTO.getLanguagesSpoken())
                .yearsOfExperience(doctorDTO.getYearsOfExperience())
                .designation(doctorDTO.getDesignation())
                .licenseNumber(doctorDTO.getLicenseNumber())
                .hospitalOrClinicName(doctorDTO.getHospitalOrClinicName())
                .availabilityStatus(doctorDTO.getAvailabilityStatus())
                .degrees(doctorDTO.getDegrees())
                .profilePictureUrl(doctorDTO.getProfilePictureUrl())
                .doctorName(doctorDTO.getDoctorName())
                .build();
        MUser mUser=MUser.builder()
                .email(doctorDTO.getEmail())
                .isActive(false)
                .password(passwordEncoder.encode(doctorDTO.getPassword()))
                .name(doctorDTO.getDoctorName())
                .gender(doctorDTO.getGender())
                .contact(doctorDTO.getContactNumber())
                .dateOfBirth(doctorDTO.getDateOfBirth())
                .doctorProfile(doctorProfile)
                .build();
        UserRole userRole=UserRole.builder()
                        .role("DOCTOR")
                        .users(Set.of(mUser))
                        .build();
        mUser.setUserRoles(Set.of(userRole));
        doctorProfile.setUser(mUser);
        return doctorProfile;
    }

    public DoctorDTO toDoctorDTO(DoctorProfile doctorProfile) {
        return DoctorDTO.builder()
                .id(doctorProfile.getId())
                .languagesSpoken(doctorProfile.getLanguagesSpoken())
                .yearsOfExperience(doctorProfile.getYearsOfExperience())
                .designation(doctorProfile.getDesignation())
                .licenseNumber(doctorProfile.getLicenseNumber())
                .hospitalOrClinicName(doctorProfile.getHospitalOrClinicName())
                .availabilityStatus(doctorProfile.getAvailabilityStatus())
                .degreesString(doctorProfile.getDegrees().toString())
                .doctorName(doctorProfile.getUser().getName())
                .email(doctorProfile.getUser().getEmail())
                .contactNumber(doctorProfile.getUser().getContact())
                .gender(doctorProfile.getUser().getGender())
                .dateOfBirth(doctorProfile.getUser().getDateOfBirth())
                .profilePictureUrl(doctorProfile.getProfilePictureUrl())
                .build();
    }

    public List<DoctorDTO> toDoctorDTOs(List<DoctorProfile> all) {

        List<DoctorDTO>doctorDTOS=new ArrayList<>();
        for (DoctorProfile doctorProfile :all){
            doctorDTOS.add(toDoctorDTO(doctorProfile));
        }
        return doctorDTOS;
    }
}
