package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.DoctorBooking;
import com.example.Appointment.System.model.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<PatientProfile,Long> {
  Optional<PatientProfile> findByPatientName(String patientName);

  @Query("SELECT pp FROM PatientProfile pp WHERE pp.user.contact = :patientContact")
  Optional<PatientProfile> findByPatientContact(@Param("patientContact") String patientContact);

}
