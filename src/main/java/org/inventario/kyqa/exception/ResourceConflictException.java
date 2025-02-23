package org.inventario.kyqa.exception;

public class ResourceConflictException extends ApiException {
    public ResourceConflictException(String message) {
        super(message, "CONFLICT", ErrorType.CONFLICT);
    }
}
