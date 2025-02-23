package org.inventario.kyqa.exception;

public class ResourceBadRequestException extends ApiException {
    public ResourceBadRequestException(String message) {
        super(message, "BAD_REQUEST", ErrorType.BAD_REQUEST);
    }
}
