package org.inventario.kyqa.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.inventario.kyqa.dtos.EstadoDto;
import org.inventario.kyqa.dtos.SubareaDto;
import org.inventario.kyqa.entities.Subarea;
import org.inventario.kyqa.exception.ResourceConflictException;
import org.inventario.kyqa.exception.ResourceNotFoundException;
import org.inventario.kyqa.repository.SubareaRepository;
import org.inventario.kyqa.services.SubareaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "subareaService")
@Slf4j
public class SubareaServiceImpl implements SubareaService {

    private final SubareaRepository subareaRepository;
    private final ModelMapper modelMapper;

    public SubareaServiceImpl(SubareaRepository subareaRepository, ModelMapper modelMapper) {
        this.subareaRepository = subareaRepository;
        this.modelMapper = modelMapper;
    }

    public List<SubareaDto> toDtos(List<Subarea> subareas) {
        List<SubareaDto> dtos = subareas.stream()
                .map(subarea -> modelMapper.map(subarea, SubareaDto.class))
                .toList();
        return dtos;
    }

    @Override
    public List<SubareaDto> findAll() {
        List<Subarea> subareas = subareaRepository.findAll();
        if (subareas.isEmpty()) {
            log.warn("No subareas found");
            throw new ResourceNotFoundException("No subareas found");
        }
        return toDtos(subareas);
    }

    @Override
    public SubareaDto findByNombre(String nombre) {
        Subarea subarea = subareaRepository.findByNombre(nombre);
        if (subarea == null) {
            log.warn("No subarea found");
            throw new ResourceNotFoundException("No subarea found");
        }
        return modelMapper.map(subarea, SubareaDto.class);
    }

    @Override
    public List<SubareaDto> findByNombreContaining(String nombre) {
        List<Subarea> subareas = subareaRepository.findSubareasByNombreContaining(nombre);
        if (subareas.isEmpty()) {
            log.warn("No subareas found");
            throw new ResourceNotFoundException("No subareas found");
        }
        return toDtos(subareas);
    }

    @Override
    public ResponseEntity<SubareaDto> create(String nombre) {
        nombre = nombre.toUpperCase();
        Subarea subarea = subareaRepository.findByNombre(nombre);
        if (subareaRepository.existsByNombre(nombre)) {
            log.warn(String.format("Subarea with name %s already exists", nombre));
            throw new ResourceConflictException(String.format("Subarea with name %s already exists", nombre));
        }
        Subarea newSubarea  = Subarea.builder()
                .nombre(nombre)
                .build();
        subareaRepository.save(newSubarea);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(newSubarea.getId())
                .toUri();
        log.info("Subarea created successfully");
        return ResponseEntity.created(location).body(modelMapper.map(newSubarea, SubareaDto.class));
    }

    @Override
    public ResponseEntity<String> deleteById(Integer id) {
        if (subareaRepository.existsById(id)) {
            subareaRepository.deleteById(id);
            return new ResponseEntity<>(String.format("Subarea with id %d id delete successfully", id), HttpStatus.OK);
        }
        log.warn("Subarea with id {} not found", id);
        throw new ResourceNotFoundException(String.format("Subarea with id %d id not found", id));
    }

    @Override
    public ResponseEntity<String> deleteByNombre(String nombre) {
        nombre = nombre.toUpperCase();
        if (subareaRepository.existsByNombre(nombre)) {
            subareaRepository.deleteByNombre(nombre);
            return new ResponseEntity<>(String.format("Subarea with name %s delete successfully", nombre), HttpStatus.OK);
        }
        log.warn("Subarea with name {} not found", nombre);
        throw new ResourceNotFoundException(String.format("Subarea with name %s not found", nombre));
    }

    @Override
    public ResponseEntity<SubareaDto> update(Integer id, String nombre) {
        nombre = nombre.toUpperCase();
        if (subareaRepository.existsByNombre(nombre)) {
            log.warn(String.format("Subarea with name %s already exists", nombre));
            throw new ResourceConflictException(String.format("Subarea with name %s already exists", nombre));
        }
        if (subareaRepository.existsById(id)) {
            Optional<Subarea> newSubarea = subareaRepository.findById(id);
            newSubarea.get().setNombre(nombre);
            subareaRepository.save(newSubarea.get());
            return ResponseEntity.ok(modelMapper.map(subareaRepository.findById(id), SubareaDto.class));
        }
        log.warn("Estado with id {} not found", id);
        throw new ResourceNotFoundException(String.format("Estado with id %d not found", id));
    }
}
