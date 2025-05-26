package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.DoctorAppointmentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoctorAppointmentDateRepo extends JpaRepository<DoctorAppointmentDate,Long> {
    Optional<List<DoctorAppointmentDate>> findByAppointmentDate(LocalDate date);
    @Query("SELECT d.timeSlots FROM DoctorAppointmentDate d " +
            "JOIN d.doctorProfiles dp " +
            "WHERE dp.id = :doctorId AND d.appointmentDate = :date")
    List<String> findTimeSlotsByDoctorIdAndDate(@Param("doctorId") Long doctorId,
                                                @Param("date") LocalDate date);

}
