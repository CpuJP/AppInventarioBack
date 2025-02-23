package org.inventario.kyqa.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


import java.util.Map;

@ControllerAdvice // Corregida la escritura
public class GraphQLExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleApiException(ApiException ex, DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(mapToGraphQLError(ex.getErrorType())) // Usar ErrorType de Spring
                .message(ex.getMessage())
                .extensions(Map.of("code", ex.getErrorCode())) // En lugar de .extension()
                .path(env.getExecutionStepInfo().getPath())
                .build();
    }

    private ErrorClassification mapToGraphQLError(org.inventario.kyqa.exception.ErrorType errorType) {
        return switch (errorType) {
            case NOT_FOUND -> ErrorType.NOT_FOUND;
            case BAD_REQUEST -> ErrorType.BAD_REQUEST;
            case UNAUTHORIZED -> ErrorType.UNAUTHORIZED;
            case CONFLICT -> ErrorType.BAD_REQUEST; // Nombre corregido
            case NO_CONTENT -> ErrorType.NOT_FOUND; // Usar el tipo correcto
            default -> ErrorType.INTERNAL_ERROR;
        };
    }
}