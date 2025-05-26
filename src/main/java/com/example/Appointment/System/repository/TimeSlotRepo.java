package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeSlotRepo extends JpaRepository<TimeSlot,Long> {
}
