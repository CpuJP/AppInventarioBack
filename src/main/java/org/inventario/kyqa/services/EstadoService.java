package org.inventario.kyqa.services;

import org.inventario.kyqa.dtos.EstadoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EstadoService {

    List<EstadoDto> findAll();

    EstadoDto findByNombre(String nombre);

    List<EstadoDto> findByNombreContaining(String nombre);

    ResponseEntity<EstadoDto> create(@PathVariable String nombre);

    ResponseEntity<String> deleteById(@PathVariable Integer id);

    ResponseEntity<String> deleteByNombre(@PathVariable String nombre);

    ResponseEntity<EstadoDto> update(@PathVariable Integer id, @PathVariable String nombre);
}
