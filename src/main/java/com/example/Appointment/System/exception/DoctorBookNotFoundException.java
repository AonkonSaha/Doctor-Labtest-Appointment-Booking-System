package com.example.Appointment.System.exception;

public class DoctorBookNotFoundException extends RuntimeException {
  public DoctorBookNotFoundException(String message) {
    super(message);
  }
}
