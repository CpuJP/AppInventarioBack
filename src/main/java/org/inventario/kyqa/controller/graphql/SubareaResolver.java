package org.inventario.kyqa.controller.graphql;

import org.inventario.kyqa.dtos.SubareaDto;
import org.inventario.kyqa.services.SubareaService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SubareaResolver {

    private final SubareaService subareaService;

    public SubareaResolver(SubareaService subareaService) {
        this.subareaService = subareaService;
    }

    @QueryMapping(name = "findAllSubareas")
    public List<SubareaDto> findAll() {
        return subareaService.findAll();
    }

    @QueryMapping(name = "findSubareaByNombre")
    public SubareaDto findByNombre(@Argument(name = "nombre") String nombre) {
        return subareaService.findByNombre(nombre);
    }

    @QueryMapping(name = "findSubareasByNombreContaining")
    public List<SubareaDto> findByNombreContaining(@Argument(name = "nombre") String nombre) {
        return subareaService.findByNombreContaining(nombre);
    }
}
