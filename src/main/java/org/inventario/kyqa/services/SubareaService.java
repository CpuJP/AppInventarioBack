package org.inventario.kyqa.services;

import org.inventario.kyqa.dtos.SubareaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SubareaService {

    List<SubareaDto> findAll();

    SubareaDto findByNombre(String nombre);

    List<SubareaDto> findByNombreContaining(String nombre);

    ResponseEntity<SubareaDto> create(@PathVariable String nombre);

    ResponseEntity<String> deleteById(@PathVariable Integer id);

    ResponseEntity<String> deleteByNombre(@PathVariable String nombre);

    ResponseEntity<SubareaDto> update(@PathVariable Integer id, @PathVariable String nombre);
}
