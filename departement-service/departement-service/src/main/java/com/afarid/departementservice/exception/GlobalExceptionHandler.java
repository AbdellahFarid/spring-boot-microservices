package com.afarid.departementservice.exception;

import com.afarid.departementservice.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(LocalDateTime.now());
        error.setMessage(e.getMessage());
        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setUriPath(request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
