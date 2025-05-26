package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.LabTestBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestBookingRepo extends JpaRepository<LabTestBooking,Long> {
    @Query("SELECT ltb FROM LabTestBooking ltb WHERE ltb.patientProfile.patientName = :patientName")
    List<LabTestBooking> findAllByUserName(@Param("patientName") String patientName);

    @Query("SELECT ltb FROM LabTestBooking ltb WHERE ltb.patientProfile.user.contact = :patientContact")
    List<LabTestBooking> findAllByUserContact(@Param("patientContact") String patientContact);
}
