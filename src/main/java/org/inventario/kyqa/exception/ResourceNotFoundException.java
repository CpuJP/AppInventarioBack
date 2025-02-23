package org.inventario.kyqa.exception;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String message) {
        super(message, "RESOURCE_NOT_FOUND", ErrorType.NOT_FOUND);
    }
}
