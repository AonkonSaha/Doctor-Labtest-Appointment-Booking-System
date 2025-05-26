package com.example.Appointment.System.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordDTO {

    private String password;
    private String newPassword;
    private String confirmPassword;

}
