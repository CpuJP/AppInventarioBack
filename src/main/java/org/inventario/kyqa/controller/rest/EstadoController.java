package org.inventario.kyqa.controller.rest;

import org.inventario.kyqa.dtos.EstadoDto;
import org.inventario.kyqa.services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @PostMapping("{nombre}")
    public ResponseEntity<EstadoDto> createEstado(@PathVariable String nombre) {
        return estadoService.create(nombre);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEstado(@PathVariable Integer id) {
        return estadoService.deleteById(id);
    }

    @DeleteMapping("/nombre/{nombre}")
    public ResponseEntity<String> deleteEstadoByNombre(@PathVariable String nombre) {
        return estadoService.deleteByNombre(nombre);
    }

    @PatchMapping
    public ResponseEntity<EstadoDto> updateEstado(@RequestParam Integer id, @RequestParam String nombre) {
        return estadoService.update(id, nombre);
    }
}
