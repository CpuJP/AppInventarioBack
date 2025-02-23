package org.inventario.kyqa.exception;

import lombok.Getter;

@Getter
public abstract class ApiException extends RuntimeException {
    private final String errorCode;
    private final ErrorType errorType; // Enum común para clasificación

    public ApiException(String message, String errorCode, ErrorType errorType) {
        super(message);
        this.errorCode = errorCode;
        this.errorType = errorType;
    }
}

