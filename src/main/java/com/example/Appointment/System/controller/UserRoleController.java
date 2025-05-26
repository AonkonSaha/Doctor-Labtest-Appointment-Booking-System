package com.example.Appointment.System.controller;

import com.example.Appointment.System.model.dto.UserRoleDTO;
import com.example.Appointment.System.model.mapper.UserRoleMapper;
import com.example.Appointment.System.service.Imp.UserRoleServiceImp;
import com.example.Appointment.System.service.UserRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/role")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserRoleController {
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;
    @PostMapping("/set")
    public ResponseEntity<UserRoleDTO> setUserRole(@RequestBody UserRoleDTO userRoleDTO){
        return ResponseEntity.ok(userRoleMapper.toUserRoleDTO(userRoleService.saveUserRole(
                        userRoleMapper.toUserRole(userRoleDTO))));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserRoleDTO> updateUserRoleById(@PathVariable("id") Long id,@RequestBody UserRoleDTO userRoleDTO){
        return ResponseEntity.ok(
                userRoleMapper.toUserRoleDTO(userRoleService.updateRoleById(id,userRoleDTO))
        );
    }

}
