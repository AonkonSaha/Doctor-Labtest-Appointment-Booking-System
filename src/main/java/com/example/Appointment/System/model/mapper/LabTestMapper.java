package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.LabTestDTO;
import com.example.Appointment.System.model.entity.LabTest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LabTestMapper {
    public LabTest toLabTest(LabTestDTO labTestDTO) {
        return LabTest.builder()
                .labTestName(labTestDTO.getLabTestName())
                .labTestImageUrl(labTestDTO.getLabTestImageUrl())
                .build();
    }

    public LabTestDTO toLabTestDTO(LabTest labTest) {
        return LabTestDTO.builder()
                .id(labTest.getId())
                .labTestName(labTest.getLabTestName())
                .labTestImageUrl(labTest.getLabTestImageUrl())
                .build();
    }

    public List<LabTestDTO> toLabTestDTOS(List<LabTest> allLabTest) {
        List<LabTestDTO>labTestDTOS=new ArrayList<>();
        for (LabTest labTest :allLabTest){
            labTestDTOS.add(toLabTestDTO(labTest));
        }
        return labTestDTOS;
    }
}
