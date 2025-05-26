package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.DoctorBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorBookingRepo extends JpaRepository<DoctorBooking,Long> {
    @Query("SELECT db FROM DoctorBooking db WHERE db.patientProfile.patientName = :userName")
    List<DoctorBooking> findAllByPatientName(@Param("userName") String userName);

    @Query("SELECT db FROM DoctorBooking db WHERE db.patientProfile.user.contact = :patientContact")
    List<DoctorBooking> findAllByPatientContact(@Param("patientContact") String patientContact);
}
