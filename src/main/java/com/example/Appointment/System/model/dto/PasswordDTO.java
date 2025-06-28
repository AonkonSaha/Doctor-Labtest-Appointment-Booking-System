package com.example.Appointment.System.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "Password", description = "DTO for updating user password.")
public class PasswordDTO {

    @Schema(
            description = "Current password of the user.",
            example = "oldPassword123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String password;

    @Schema(
            description = "New password to set.",
            example = "newSecurePassword456",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String newPassword;

    @Schema(
            description = "Confirmation of the new password. Should match `newPassword`.",
            example = "newSecurePassword456",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String confirmPassword;
}
