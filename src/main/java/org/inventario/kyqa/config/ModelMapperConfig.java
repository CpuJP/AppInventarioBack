package org.inventario.kyqa.config;

import org.inventario.kyqa.dtos.AuditoriaDto;
import org.inventario.kyqa.dtos.VistaInventarioItemDto;
import org.inventario.kyqa.entities.Auditoria;
import org.inventario.kyqa.entities.VistaInventariosItems;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setDeepCopyEnabled(true);
        modelMapper.createTypeMap(Auditoria.class, AuditoriaDto.class)
                .addMappings(mapper -> mapper.using(ctx -> (Instant) ctx.getSource()).map(Auditoria::getFecha, AuditoriaDto::setFecha));
        modelMapper.createTypeMap(VistaInventariosItems.class, VistaInventarioItemDto.class)
                .addMappings(mapper -> mapper.using(ctx -> (Instant) ctx.getSource()).map(VistaInventariosItems::getUltimaActualizacion, VistaInventarioItemDto::setUltimaActualizacion))
                .addMappings(mapper -> mapper.using(ctx -> (Instant) ctx.getSource()).map(VistaInventariosItems::getFechaCreacion, VistaInventarioItemDto::setFechaCreacion));
        return modelMapper;
    }
}
