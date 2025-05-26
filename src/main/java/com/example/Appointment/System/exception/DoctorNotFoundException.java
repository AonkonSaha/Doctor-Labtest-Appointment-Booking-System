package com.example.Appointment.System.exception;

public class DoctorNotFoundException extends Throwable {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
