package com.example.Appointment.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Login", description = "DTO for user login credentials.")
public class LoginDTO {

    @Schema(
            description = "Contact number or email used for login.",
            example = "+8801881264859",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String contact;

    @Schema(
            description = "Password for the user account.",
            example = "strongPassword123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    @Schema(
            description = "Optional username (may be used alternatively).",
            example = "aonkon.saha"
    )
    private String username;
}
