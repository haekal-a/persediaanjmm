package com.tamu.util;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.util.List;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private List<ObjectError> errors;

    public ApiError(HttpStatus status, String message, List<ObjectError> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
