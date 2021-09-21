//package com.kondangan.controller.exception;
//
//import com.kondangan.util.ApiError;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class CustomRestExceptionHandler {
//
//    @ExceptionHandler({BindException.class})
//    public ResponseEntity<Object> bindExceptionHandler(BindException ex){
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getBindingResult().getAllErrors());
//        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
//    }
//}
