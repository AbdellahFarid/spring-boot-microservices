package com.afarid.employeeservice.exception;


import com.afarid.employeeservice.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException e,
            WebRequest request){
        ErrorDetails errorMessage = new ErrorDetails();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setMessage(e.getMessage());
        errorMessage.setCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setUriPath(request.getDescription(false));

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
