package com.afarid.departementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> fieldErrors = new HashMap<>();
    private int code;
    private String uriPath;

    public void addFields(String field, String message){
        fieldErrors.put(field, message);
    }
}
