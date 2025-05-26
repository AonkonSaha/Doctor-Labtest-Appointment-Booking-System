package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.enums.Status;
import com.example.Appointment.System.exception.PatientNotFoundException;
import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.entity.DoctorAppointmentDate;
import com.example.Appointment.System.model.entity.DoctorProfile;
import com.example.Appointment.System.model.entity.DoctorBooking;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.example.Appointment.System.repository.DoctorAppointmentDateRepo;
import com.example.Appointment.System.repository.DoctorBookingRepo;
import com.example.Appointment.System.repository.DoctorRepo;
import com.example.Appointment.System.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DoctorBookingMapper {
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final DoctorAppointmentDateRepo doctorAppointmentDateRepo;
    public DoctorBookingDTO toDoctorBookingDTO(DoctorBooking doctorBooking) {

        return DoctorBookingDTO.builder()
                .bookingDate(doctorBooking.getBookingDate())
                .appointmentDate(doctorBooking.getAppointmentDate())
                .note(doctorBooking.getNote())
                .status(doctorBooking.getStatus())
                .designation(doctorBooking.getDoctorProfile().getDesignation())
                .degrees(doctorBooking.getDoctorProfile().getDegrees() )
                .hospitalOrClinicName(doctorBooking.getDoctorProfile().getHospitalOrClinicName())
                .licenseNumber(doctorBooking.getDoctorProfile().getLicenseNumber())
                .status(doctorBooking.getStatus())
                .doctorName(doctorBooking.getDoctorProfile().getDoctorName())
                .doctorId(doctorBooking.getDoctorProfile().getId())
                .doctorBookingId(doctorBooking.getId())
                .slotTime(doctorBooking.getSlotTime())
                .doctorImageUrl(doctorBooking.getDoctorProfile().getProfilePictureUrl())
                .build();
    }
    public DoctorBooking toDoctorBooking(DoctorBookingDTO doctorBookingDTO)  {
        String patientContact= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<PatientProfile> patient=patientRepo.findByPatientContact(patientContact);
        Optional<DoctorProfile> doctor=doctorRepo.findById(doctorBookingDTO.getDoctorId());
        if(patient.isEmpty()){
            throw new IllegalArgumentException("Patient doesn't found");
        }
        if(doctor.isEmpty()){
            throw new IllegalArgumentException("Doctor doesn't exit");
        }

        List<DoctorAppointmentDate> existingDates = doctorAppointmentDateRepo.findByAppointmentDate(doctorBookingDTO.getBookingDate())
                .orElse(new ArrayList<>());
        DoctorAppointmentDate targetDate = null;

        for (DoctorAppointmentDate date : existingDates) {
            if (date.getDoctorProfiles().contains(doctor)) {
                targetDate = date;
                break;
            }
        }
        if (targetDate == null) {
            targetDate = new DoctorAppointmentDate();
            targetDate.setAppointmentDate(doctorBookingDTO.getBookingDate());
            targetDate.setTimeSlots(new ArrayList<>(List.of(doctorBookingDTO.getSlotTime())));
            targetDate.getDoctorProfiles().add(doctor.get());
            doctor.get().getDoctorAppointmentDates().add(targetDate);
        } else {
            if (!targetDate.getTimeSlots().contains(doctorBookingDTO.getSlotTime())) {
                targetDate.getTimeSlots().add(doctorBookingDTO.getSlotTime());
            }
        }

        doctorAppointmentDateRepo.save(targetDate);

        DoctorBooking doctorBooking= DoctorBooking.builder()
                .bookingDate(doctorBookingDTO.getBookingDate())
                .appointmentDate(doctorBookingDTO.getBookingDate())
                .note(doctorBookingDTO.getNote())
                .slotTime(doctorBookingDTO.getSlotTime())
                .status(Status.Confirmed.name())
                .build();
        patient.get().setDoctorBookings(Set.of(doctorBooking));
        doctor.get().setDoctorBookings(Set.of(doctorBooking));
        patient.get().setDoctorProfiles(Set.of(doctor.get()) );
        doctor.get().setPatientProfiles(Set.of(patient.get()) );
//        doctorAppointmentDate.setDoctorProfiles(Set.of(doctor.get()));
//        doctor.get().setDoctorAppointmentDates(Set.of(doctorAppointmentDate));
        doctorBooking.setPatientProfile(patient.get());
        doctorBooking.setDoctorProfile(doctor.get());
        return doctorBooking;

    }


    public List<DoctorBookingDTO> toDoctorBookingDTOS(List<DoctorBooking> doctorBookings) {
        List<DoctorBookingDTO>doctorBookingDTOS=new java.util.ArrayList<>();
        for (DoctorBooking doctorBooking:doctorBookings){
            doctorBookingDTOS.add(toDoctorBookingDTO(doctorBooking));
        }
        return doctorBookingDTOS;
    }
}
