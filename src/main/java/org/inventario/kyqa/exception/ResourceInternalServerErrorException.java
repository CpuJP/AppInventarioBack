package org.inventario.kyqa.exception;

public class ResourceInternalServerErrorException extends ApiException {
    public ResourceInternalServerErrorException(String message) {
        super(message, "INTERNAL_SERVER_ERROR", ErrorType.INTERNAL_ERROR);
    }
}
