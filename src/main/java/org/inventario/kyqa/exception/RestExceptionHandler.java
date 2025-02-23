package org.inventario.kyqa.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        return ResponseEntity.status(mapToHttpStatus(ex.getErrorType()))
                .body(new ErrorResponse(ex.getErrorCode(), ex.getMessage()));
    }

    private HttpStatus mapToHttpStatus(ErrorType errorType) {
        return switch (errorType) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case BAD_REQUEST -> HttpStatus.BAD_REQUEST;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case CONFLICT -> HttpStatus.CONFLICT;
            case NO_CONTENT -> HttpStatus.NO_CONTENT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    @Data
    @AllArgsConstructor
    static class ErrorResponse {
        private String code;
        private String message;
    }
}
