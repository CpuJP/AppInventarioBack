package org.inventario.kyqa.services;

import org.inventario.kyqa.dtos.AuditoriaDto;


import java.time.Instant;
import java.util.List;

public interface AuditoriaService {

    List<AuditoriaDto> findAll();

    List<AuditoriaDto> findByTipoOperacion(String tipoOperacion);

    List<AuditoriaDto> findByFechaBetween(Instant fechaInicial, Instant fechaFinal);

    List<AuditoriaDto> findByTabla(String tabla);
}
