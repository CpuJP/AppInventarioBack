package org.inventario.kyqa.config;

import graphql.scalars.ExtendedScalars;
import org.inventario.kyqa.dtos.AuditoriaDto;
import org.inventario.kyqa.dtos.VistaInventarioItemDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.Instant;
import java.time.ZoneOffset;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.DateTime)
                .type("Auditoria", typeWiring -> typeWiring
                        // Usar AuditoriaDto como fuente de datos
                        .dataFetcher("fecha", env -> {
                            AuditoriaDto dto = env.getSource(); // ¡Ahora es el DTO!
                            return dto.getFecha();
                        })
                )
                .type("VistaInventarioItem", typeWiring -> typeWiring
                        .dataFetcher("ultimaActualizacion", env -> {
                            VistaInventarioItemDto dto = env.getSource();
                            return dto.getUltimaActualizacion();
                        })
                );
    }
}
