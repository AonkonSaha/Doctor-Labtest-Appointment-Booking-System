package com.example.Appointment.System.service.Imp;

import com.example.Appointment.System.model.dto.UserRoleDTO;
import com.example.Appointment.System.model.entity.UserRole;
import com.example.Appointment.System.repository.UserRoleRepo;
import com.example.Appointment.System.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImp implements UserRoleService {
    private final UserRoleRepo userRoleRepo;

    @Override
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public UserRole updateRoleById(Long id, UserRoleDTO userRoleDTO) {
        UserRole userRole=userRoleRepo.findById(id).get();
        userRole.setRole(userRoleDTO.getRole());
        return userRoleRepo.save(userRole);
    }
}
