package org.inventario.kyqa.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.inventario.kyqa.dtos.EstadoDto;
import org.inventario.kyqa.entities.Estado;
import org.inventario.kyqa.exception.ResourceConflictException;
import org.inventario.kyqa.exception.ResourceNotFoundException;
import org.inventario.kyqa.repository.EstadoRepository;
import org.inventario.kyqa.services.EstadoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service(value = "estadoService")
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;
    private final ModelMapper modelMapper;

    public EstadoServiceImpl(EstadoRepository estadoRepository, ModelMapper modelMapper) {
        this.estadoRepository = estadoRepository;
        this.modelMapper = modelMapper;
    }

    public List<EstadoDto> toDto(List<Estado> estados) {
        List<EstadoDto> dtos = estados.stream()
                .map(estado -> modelMapper.map(estado, EstadoDto.class))
                .toList();
        return dtos;
    }

    @Override
    public List<EstadoDto> findAll() {
        List<Estado> estados = estadoRepository.findAll();
        if (estados.isEmpty()) {
            log.warn("No Estado found");
            throw new ResourceNotFoundException("No Estado found");
        }
        return toDto(estados);
    }

    @Override
    public EstadoDto findByNombre(String nombre) {
        Estado estado = estadoRepository.findByNombre(nombre);
        if (estado == null) {
            log.warn("Estado not found");
            throw new ResourceNotFoundException("Estado not found");
        }
        return modelMapper.map(estado, EstadoDto.class);
    }

    @Override
    public List<EstadoDto> findByNombreContaining(String nombre) {
        List<Estado> estados = estadoRepository.findEstadosByNombreContaining(nombre);
        if (estados.isEmpty()) {
            log.warn("Estado not found");
            throw new ResourceNotFoundException("Estado not found");
        }
        return toDto(estados);
    }

    @Override
    public ResponseEntity<EstadoDto> create(String nombre) {
        nombre = nombre.toUpperCase();
        Estado estado = estadoRepository.findByNombre(nombre);
        if (estadoRepository.existsByNombre(nombre)) {
            log.warn(String.format("Estado with name %s already exists", nombre));
            throw new ResourceConflictException(String.format("Estado with name %s already exists", nombre));
        }
        Estado newEstado = Estado.builder()
                .nombre(nombre)
                .build();
        estadoRepository.save(newEstado);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(newEstado.getId())
                .toUri();
        log.info("Estado created successfully");
        return ResponseEntity.created(location).body(modelMapper.map(newEstado, EstadoDto.class));
    }

    @Override
    public ResponseEntity<String> deleteById(Integer id) {
        if (estadoRepository.existsById(id)) {
            estadoRepository.deleteById(id);
            return new  ResponseEntity<>(String.format("Estado with id %d is delete successfully", id), HttpStatus.OK);
        }
        log.warn("Estado with id {} not found", id);
        throw new ResourceNotFoundException(String.format("Estado with id %d not found", id));
    }

    @Override
    public ResponseEntity<String> deleteByNombre(String nombre) {
        nombre = nombre.toUpperCase();
        if (estadoRepository.existsByNombre(nombre)) {
            estadoRepository.deleteByNombre(nombre);
            return new  ResponseEntity<>(String.format("Estado with name %s is delete successfully", nombre), HttpStatus.OK);
        }
        log.warn("Estado with name {} not found", nombre);
        throw new ResourceNotFoundException(String.format("Estado with name %s not found", nombre));
    }

    @Override
    public ResponseEntity<EstadoDto> update(Integer id, String nombre) {
        nombre = nombre.toUpperCase();
        if (estadoRepository.existsByNombre(nombre)) {
            log.warn(String.format("Estado with name %s already exists", nombre));
            throw new ResourceConflictException(String.format("Estado with name %s already exists", nombre));
        }
        if (estadoRepository.existsById(id)) {
            Optional<Estado> newEstado = estadoRepository.findById(id);
            newEstado.get().setNombre(nombre);
            estadoRepository.save(newEstado.get());
            return ResponseEntity.ok(modelMapper.map(estadoRepository.findById(id), EstadoDto.class));
        }
        log.warn("Estado with id {} not found", id);
        throw new ResourceNotFoundException(String.format("Estado with id %d not found", id));
    }
}
