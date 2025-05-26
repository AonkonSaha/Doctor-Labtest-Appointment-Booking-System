package com.example.Appointment.System.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {

    @NotNull
    private String contact;
    @NotNull
    private String password;
    private String username;
}
