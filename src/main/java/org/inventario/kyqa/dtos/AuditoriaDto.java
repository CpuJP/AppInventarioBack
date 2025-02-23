package org.inventario.kyqa.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
public class AuditoriaDto implements Serializable {

    private Integer id;

    private String tabla;

    private String tipoOperacion;

    private String datosAnteriores;

    private String datosNuevos;

    private String usuario;

    private Instant fecha;

    // Getter modificado para GraphQL
    public OffsetDateTime getFecha() {
        return this.fecha != null
                ? this.fecha.atOffset(ZoneOffset.UTC)
                : null;
    }
}
