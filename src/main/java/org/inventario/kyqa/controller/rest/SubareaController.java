package org.inventario.kyqa.controller.rest;

import org.inventario.kyqa.dtos.SubareaDto;
import org.inventario.kyqa.services.SubareaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subarea")
public class SubareaController {

    private final SubareaService subareaService;

    public SubareaController(SubareaService subareaService) {
        this.subareaService = subareaService;
    }

    @PostMapping("{nombre}")
    public ResponseEntity<SubareaDto> createSubarea(@PathVariable String nombre) {
       return subareaService.create(nombre);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSubarea(@PathVariable Integer id) {
        return subareaService.deleteById(id);
    }

    @DeleteMapping("/nombre/{nombre}")
    public ResponseEntity<String> deleteSubareaByNombre(@PathVariable String nombre) {
        return subareaService.deleteByNombre(nombre);
    }

    @PatchMapping
    public ResponseEntity<SubareaDto> updateSubarea(@RequestParam Integer id, @RequestParam String nombre) {
        return subareaService.update(id, nombre);
    }
}
