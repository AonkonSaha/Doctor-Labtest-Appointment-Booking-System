package com.example.Appointment.System.controller;

import com.example.Appointment.System.constant.ApiPaths;
import com.example.Appointment.System.model.dto.UserRoleDTO;
import com.example.Appointment.System.model.mapper.UserRoleMapper;
import com.example.Appointment.System.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.UserRole.ROOT)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User Role API", description = "Manage user role assignments")
public class UserRoleController {

    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;

    @Operation(summary = "Assign a role to a user")
    @PostMapping(ApiPaths.UserRole.SET)
    public ResponseEntity<UserRoleDTO> setUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        return ResponseEntity.ok(
                userRoleMapper.toUserRoleDTO(
                        userRoleService.saveUserRole(userRoleMapper.toUserRole(userRoleDTO))
                )
        );
    }

    @Operation(summary = "Update user role by user role ID")
    @PutMapping(ApiPaths.UserRole.UPDATE)
    public ResponseEntity<UserRoleDTO> updateUserRoleById(
            @Parameter(description = "ID of the user role to update", example = "1")
            @PathVariable("id") Long id,
            @RequestBody UserRoleDTO userRoleDTO) {
        return ResponseEntity.ok(
                userRoleMapper.toUserRoleDTO(
                        userRoleService.updateRoleById(id, userRoleDTO)
                )
        );
    }
}
