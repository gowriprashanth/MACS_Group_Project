package com.project.accomatch.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.accomatch.Exception.InvalidInputException;
import com.project.accomatch.Exception.InvalidPostStatusException;
import com.project.accomatch.Exception.ResourceNotFoundException;
import com.project.accomatch.Exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidInputException.class, InvalidPostStatusException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // Create an ErrorResponse object with the relevant error details
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), new Date());

        // Return a ResponseEntity with the ErrorResponse and appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(), new Date());

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), new Date());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Create an ErrorResponse object with the relevant error details
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());

        // Return a ResponseEntity with the ErrorResponse and appropriate HTTP status
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    public class ErrorResponse {
        @JsonProperty("status")
        private int statusCode;

        @JsonProperty("message")
        private String errorMessage;

        @JsonProperty("timestamp")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private Date timestamp;

        public ErrorResponse(int status, String message, Date timestamp) {
            this.statusCode = status;
            this.errorMessage = message;
            this.timestamp = timestamp;
        }

    }


}
