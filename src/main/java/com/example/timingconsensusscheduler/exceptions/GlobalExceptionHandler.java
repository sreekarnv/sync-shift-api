package com.example.timingconsensusscheduler.exceptions;

import com.example.timingconsensusscheduler.dto.FieldErrorDto;

import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;
import java.util.stream.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<FieldErrorDto>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new FieldErrorDto(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getFieldErrorsMap(errors));
    }

    @ExceptionHandler({
            UsernameNotFoundException.class,
            NoSuchElementException.class,
    })
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(UsernameNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(getListErrorMap(errors));
    }

    @ExceptionHandler(
            DataIntegrityViolationException.class
    )
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(DataIntegrityViolationException ex) {
        String message = Objects.requireNonNull(ex.getRootCause()).getMessage();

        if (message.contains("duplicate key value violates unique constraint")) {
            String[] parts = message.split("\"");
            String detailMessage = parts[2];
            String fieldValue = detailMessage.substring(detailMessage.indexOf("(") + 1, detailMessage.indexOf(")"));
            String errorMessage = "This " + fieldValue + " is already taken.";
            List<String> errors = List.of(errorMessage);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(getListErrorMap(errors));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getListErrorMap(List.of(new String[]{"Something Went Wrong!!"})));
    }

    @ExceptionHandler(
            ValidationException.class
    )
    public ResponseEntity<Map<String, List<String>>> handleCustomValidationErrors(ValidationException ex) {
        var message = ex.getMessage();
        List<String> errors = Collections.singletonList(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(getListErrorMap(errors));
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, List<String>>> handleAuthenticationException(Exception ex) {
        var message = Objects.equals(ex.getMessage(), "Bad credentials")
                ? "Invalid Credentials. Please provide your correct email and password"
                : ex.getMessage();

        List<String> errors = Collections.singletonList(message);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(getListErrorMap(errors));
    }

    private Map<String, List<String>> getListErrorMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    private Map<String, List<FieldErrorDto>> getFieldErrorsMap(List<FieldErrorDto> errors) {
        Map<String, List<FieldErrorDto>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}