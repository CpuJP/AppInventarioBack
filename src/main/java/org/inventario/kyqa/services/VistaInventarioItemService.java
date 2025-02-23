package org.inventario.kyqa.services;

import org.inventario.kyqa.dtos.VistaInventarioItemDto;

import java.util.List;

public interface VistaInventarioItemService {

    List<VistaInventarioItemDto> findAll();
}
