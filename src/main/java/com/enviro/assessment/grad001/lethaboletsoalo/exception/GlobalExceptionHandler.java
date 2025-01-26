package com.enviro.assessment.grad001.lethaboletsoalo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice // Global exception handler for all controllers
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class) //It is thrown when an entity is not found in the database
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(), // HTTP status code 404
            "Not Found",
            ex.getMessage(),
            null
        );
        // Return a ResponseEntity with the ErrorResponse and HTTP status
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class) //It is thrown when validation of input data fails
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        ErrorResponse response = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),  // HTTP status code 400
            "Validation Error",
            ex.getMessage(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEntityException.class) // It is thrown when an attempt is made to create a duplicate entity
    public ResponseEntity<ErrorResponse> handleDuplicateEntity(DuplicateEntityException ex) {
        ErrorResponse response = new ErrorResponse(
            HttpStatus.CONFLICT.value(), // HTTP status code 409
            "Conflict",
            ex.getMessage(),
            null
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @Override // Overrides the method to handle validation errors for method arguments
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        @SuppressWarnings("null") MethodArgumentNotValidException ex,
        @SuppressWarnings("null") HttpHeaders headers,
        @SuppressWarnings("null") HttpStatusCode status,
        @SuppressWarnings("null") WebRequest request) {

        // Extract field errors and format them as a list of strings    
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(
            status.value(),
            "Validation Error",
            "Invalid request parameters",
            errors
        );
        return new ResponseEntity<>(response, headers, status);
    }

    // Handles all other exceptions that are not specifically handled by other methods
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        ErrorResponse response = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "An unexpected error occurred",
            ex.getLocalizedMessage() // Detailed error message from the exception
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}