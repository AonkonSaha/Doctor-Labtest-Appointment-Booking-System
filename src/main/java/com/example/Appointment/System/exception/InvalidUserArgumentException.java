package com.example.Appointment.System.exception;

public class InvalidUserArgumentException extends RuntimeException {
    public InvalidUserArgumentException(String message) {
        super(message);
    }
}
