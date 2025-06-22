package com.example.Appointment.System.exception;

public class InvalidLoginArgumentException extends RuntimeException {
    public InvalidLoginArgumentException(String message) {
        super(message);
    }
}
