package com.example.springboot_microservice_task_hirzinfathoni.exception;

import com.example.springboot_microservice_task_hirzinfathoni.exception.custom.InternalServerErrorException;
import com.example.springboot_microservice_task_hirzinfathoni.exception.custom.InvalidRequestException;
import com.example.springboot_microservice_task_hirzinfathoni.exception.custom.ResourceNotFoundException;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.DefaultResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.ErrorResponse;
import com.example.springboot_microservice_task_hirzinfathoni.model.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<DefaultResponse> handleInvalidRequest(InvalidRequestException ex) {
        log.warn("Bad request: {}", ex.getMessage());
        return buildDefaultError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        log.warn("Validation failed: {}", errors);
        return buildErrors(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DefaultResponse> handleNotFound(ResourceNotFoundException ex) {
        log.warn("Not found: {}", ex.getMessage());
        return buildDefaultError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<DefaultResponse> handleInternal(InternalServerErrorException ex) {
        log.error("Internal server error: {}", ex.getMessage(), ex);
        return buildDefaultError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultResponse> handleGeneral(Exception ex) {
        log.error("Unhandled exception", ex);
        return buildDefaultError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    private ResponseEntity<DefaultResponse> buildDefaultError(HttpStatus status, String detail) {
        DefaultResponse response = new DefaultResponse(ResponseMessage.ERROR, detail, status.value());
        return ResponseEntity.status(status).body(response);
    }

    private ResponseEntity<ErrorResponse> buildErrors(HttpStatus status, String detail, List<String> errors) {
        ErrorResponse response = new ErrorResponse(ResponseMessage.ERROR, detail, status.value(), errors);
        return ResponseEntity.status(status).body(response);
    }
}

