package org.inventario.kyqa.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
public class VistaInventarioItemDto implements Serializable {

    private Integer numeroItem;

    private String inventarioNombre;

    private String estadoItem;

    private String itemNombre;

    private String subAreas;

    private Integer cantidad;

    private String descripcion;

    private String observaciones;

    private Instant fechaCreacion;

    private Instant ultimaActualizacion;

    private String foto;

    public OffsetDateTime getUltimaActualizacion() {
        return this.ultimaActualizacion != null
                ? this.ultimaActualizacion.atOffset(ZoneOffset.UTC)
                : null; // Retorna null si el Instant es null
    }

    public OffsetDateTime getFechaCreacion() {
        return this.fechaCreacion != null
                ? this.fechaCreacion.atOffset(ZoneOffset.UTC)
                : null; // Retorna null si el Instant es null
    }
}
