package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.*;
import com.example.Appointment.System.model.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidDiagnosticCenterArgumentException.class)
    public ResponseEntity<ErrorResponse> handleDiagnosticCenterException(InvalidDiagnosticCenterArgumentException exception, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(DiagnosticCenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDiagnosticCenterException(DiagnosticCenterNotFoundException exception, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(DoctorBookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDoctorBookException(DoctorBookNotFoundException exception, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDoctorException(DoctorNotFoundException exception, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(InvalidDoctorArgumentException.class)
    public ResponseEntity<ErrorResponse> handleDoctorException(InvalidDoctorArgumentException exception, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(LabTestBookingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLabTestBookException(LabTestBookingNotFoundException exception, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(InvalidLabTestArgumentException.class)
    public ResponseEntity<ErrorResponse> handleLabTestException(InvalidLabTestArgumentException exception, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(InvalidUserArgumentException.class)
    public ResponseEntity<ErrorResponse> handleUserValidationException(InvalidUserArgumentException exception, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserValidationException(UserNotFoundException exception, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserValidationException(PatientNotFoundException exception, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(InvalidLoginArgumentException.class)
    public ResponseEntity<ErrorResponse> handleUserLoginException(InvalidLoginArgumentException exception, HttpServletRequest request) {
        return buildError(HttpStatus.UNAUTHORIZED, exception.getMessage(), request.getRequestURI());
    }
    @ExceptionHandler(LogoutArgumentException.class)
    public ResponseEntity<ErrorResponse> handleUserLogoutException(LogoutArgumentException exception, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String message, String path) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}


