package org.inventario.kyqa.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.inventario.kyqa.dtos.VistaInventarioItemDto;
import org.inventario.kyqa.entities.VistaInventariosItems;
import org.inventario.kyqa.exception.ResourceNotFoundException;
import org.inventario.kyqa.repository.VistaInventariosItemsRepository;
import org.inventario.kyqa.services.VistaInventarioItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "vistaInventarioItemService")
@Slf4j
public class VistaInventarioItemServiceImpl implements VistaInventarioItemService {

    private final VistaInventariosItemsRepository vistaInventariosItemsRepository;
    private final ModelMapper modelMapper;

    public VistaInventarioItemServiceImpl(VistaInventariosItemsRepository vistaInventariosItemsRepository, ModelMapper modelMapper) {
        this.vistaInventariosItemsRepository = vistaInventariosItemsRepository;
        this.modelMapper = modelMapper;
    }

    public List<VistaInventarioItemDto> toDto(List<VistaInventariosItems> vistaInventariosItems) {
        List<VistaInventarioItemDto> dtos = vistaInventariosItems.stream()
                .map(vInventarioItem -> modelMapper.map(vInventarioItem, VistaInventarioItemDto.class))
                .toList();
        return dtos;
    }

    @Override
    public List<VistaInventarioItemDto> findAll() {
        List<VistaInventariosItems> vistaInventariosItems = vistaInventariosItemsRepository.findAll();
        if (vistaInventariosItems.isEmpty()) {
            log.warn("No items found");
            throw new ResourceNotFoundException("No items found");
        }
        return toDto(vistaInventariosItems);
    }
}
