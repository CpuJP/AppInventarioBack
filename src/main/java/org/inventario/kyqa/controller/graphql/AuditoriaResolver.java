package org.inventario.kyqa.controller.graphql;

import org.inventario.kyqa.dtos.AuditoriaDto;
import org.inventario.kyqa.services.AuditoriaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/auditoria")
public class AuditoriaResolver {

    private final AuditoriaService auditoriaService;

    public AuditoriaResolver(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @QueryMapping(name = "findAllAuditorias")
    public List<AuditoriaDto> findAll() {
        return auditoriaService.findAll();
    }

    @QueryMapping(name = "findAuditoriasByTipoOperacion")
    public List<AuditoriaDto> findByTipoOperacion(@Argument(name = "tipoOperacion") String tipoOperacion) {
        return auditoriaService.findByTipoOperacion(tipoOperacion);
    }

    @QueryMapping(name = "findAuditoriasByFechaBetween")
    public List<AuditoriaDto> findByFechaBetween(@Argument(name = "fechaInicial") Instant fechaInicial, @Argument(name = "fechaFinal") Instant fechaFinal) {
        return auditoriaService.findByFechaBetween(fechaInicial, fechaFinal);
    }

    @QueryMapping(name = "findAuditoriasByTabla")
    public List<AuditoriaDto> findByTabla(@Argument(name = "tabla") String tabla) {
        return auditoriaService.findByTabla(tabla);
    }
}
