package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.UserRoleDTO;
import com.example.Appointment.System.model.entity.UserRole;
import org.springframework.stereotype.Service;


@Service
public interface UserRoleService {

    UserRole saveUserRole(UserRole userRole);
    UserRole updateRoleById(Long id, UserRoleDTO userRoleDTO);
}
