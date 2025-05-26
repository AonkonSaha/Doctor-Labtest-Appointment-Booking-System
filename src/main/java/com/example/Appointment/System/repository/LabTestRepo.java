package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestRepo extends JpaRepository<LabTest,Long> {
    boolean existsByLabTestName(String labTestName);
}
