package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.UserRoleDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.UserRole;
import com.example.Appointment.System.repository.UserRepo;
import com.example.Appointment.System.repository.UserRoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserRoleMapper {
    private final UserRoleRepo userRoleRepo;
    private final UserRepo userRepo;
    public UserRole toUserRole(UserRoleDTO userRoleDTO) {
        Set<MUser> mUsers=new HashSet<>();
        for (Long userId:userRoleDTO.getUserIds()){
            if(userRepo.findById(userId).isEmpty()){
                throw new IllegalArgumentException("User doesn't exit");
            }
            mUsers.add(userRepo.findById(userId).get());
        }
        return UserRole.builder()
                .role(userRoleDTO.getRole())
                .users(mUsers)
                .build();
    }

    public UserRoleDTO toUserRoleDTO(UserRole userRole) {
        return UserRoleDTO.builder()
                .role(userRole.getRole())
                .build();
    }
}
