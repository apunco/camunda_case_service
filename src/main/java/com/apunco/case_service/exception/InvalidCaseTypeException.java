package com.apunco.case_service.exception;

import lombok.Getter;

@Getter
public class InvalidCaseTypeException extends RuntimeException {
    private final String code;
    private final String message;

    public InvalidCaseTypeException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
