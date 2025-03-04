package com.apunco.case_service.controller;

import com.apunco.case_service.exception.InvalidCaseTypeException;
import com.apunco.openapi.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CaseServiceExceptionHandler {

    @ExceptionHandler(InvalidCaseTypeException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidCaseException(InvalidCaseTypeException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse()
                .code(ex.getCode())
                .message(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
