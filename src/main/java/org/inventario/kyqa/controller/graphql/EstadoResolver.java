package org.inventario.kyqa.controller.graphql;

import org.inventario.kyqa.dtos.EstadoDto;
import org.inventario.kyqa.services.EstadoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EstadoResolver {

    private final EstadoService estadoService;

    public EstadoResolver(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @QueryMapping(name = "findAllEstados")
    public List<EstadoDto> findAll() {
        return estadoService.findAll();
    }

    @QueryMapping(name = "findEstadoByNombre")
    public EstadoDto findByNombre(@Argument(name = "nombre") String nombre) {
        return estadoService.findByNombre(nombre);
    }

    @QueryMapping(name = "findEstadosByNombreContaining")
    public List<EstadoDto> findByNombreContaining(@Argument(name = "nombre") String nombre) {
        return estadoService.findByNombreContaining(nombre);
    }
}
