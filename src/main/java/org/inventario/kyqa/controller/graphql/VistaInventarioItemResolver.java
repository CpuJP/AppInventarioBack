package org.inventario.kyqa.controller.graphql;

import org.inventario.kyqa.dtos.VistaInventarioItemDto;
import org.inventario.kyqa.services.VistaInventarioItemService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VistaInventarioItemResolver {

    private final VistaInventarioItemService vistaInventarioItemService;

    public VistaInventarioItemResolver(VistaInventarioItemService vistaInventarioItemService) {
        this.vistaInventarioItemService = vistaInventarioItemService;
    }

    @QueryMapping(name = "findAllVistaInventarioItem")
    public List<VistaInventarioItemDto> findAll() {
        return vistaInventarioItemService.findAll();
    }
}
