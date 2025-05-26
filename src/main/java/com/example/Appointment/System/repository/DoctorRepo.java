package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<DoctorProfile,Long> {
   Optional<DoctorProfile> findByLicenseNumber(String licenseNumber);
}
