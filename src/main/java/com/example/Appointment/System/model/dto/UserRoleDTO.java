package com.example.Appointment.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Schema(name = "UserRole", description = "DTO representing a user role and associated user IDs.")
public class UserRoleDTO {

    @Schema(
            description = "Role name to assign (e.g., ROLE_ADMIN, ROLE_USER).",
            example = "ROLE_ADMIN",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String role;

    @Schema(
            description = "List of user IDs to which the role will be assigned.",
            example = "[101, 102, 103]",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<Long> userIds = new ArrayList<>();
}
