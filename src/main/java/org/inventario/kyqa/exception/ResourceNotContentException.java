package org.inventario.kyqa.exception;

public class ResourceNotContentException extends ApiException {
    public ResourceNotContentException(String message) {
        super(message, "NOT_CONTENT", ErrorType.NO_CONTENT);
    }

}
