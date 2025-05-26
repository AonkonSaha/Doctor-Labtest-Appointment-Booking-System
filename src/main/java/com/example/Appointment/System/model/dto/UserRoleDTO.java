package com.example.Appointment.System.model.dto;

import com.example.Appointment.System.model.entity.MUser;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserRoleDTO {
    @NotNull
    private String role;
    @NotNull
    private List<Long> userIds=new ArrayList<>();

}
