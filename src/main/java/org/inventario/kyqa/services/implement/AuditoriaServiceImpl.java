package org.inventario.kyqa.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.inventario.kyqa.dtos.AuditoriaDto;
import org.inventario.kyqa.entities.Auditoria;
import org.inventario.kyqa.exception.ResourceBadRequestException;
import org.inventario.kyqa.exception.ResourceNotFoundException;
import org.inventario.kyqa.repository.AuditoriaRepository;
import org.inventario.kyqa.services.AuditoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service(value = "auditoriaService")
@Slf4j
public class AuditoriaServiceImpl implements AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final ModelMapper modelMapper;

    public AuditoriaServiceImpl(AuditoriaRepository auditoriaRepository, ModelMapper modelMapper) {
        this.auditoriaRepository = auditoriaRepository;
        this.modelMapper = modelMapper;
    }

    public List<AuditoriaDto> toDto(List<Auditoria> auditorias) {
        List<AuditoriaDto> dtos = auditorias.stream()
                .map(auditoria -> modelMapper.map(auditoria, AuditoriaDto.class))
                .toList();
        return dtos;
    }

    @Override
    public List<AuditoriaDto> findAll() {
        List<Auditoria> auditorias = auditoriaRepository.findAll();
        if (auditorias.isEmpty()) {
            log.warn("No Auditoria found");
            throw new ResourceNotFoundException("No Auditoria found");
        }
        return toDto(auditorias);
    }

    @Override
    public List<AuditoriaDto> findByTipoOperacion(String tipoOperacion) {
        List<Auditoria> auditorias = auditoriaRepository.findByTipoOperacionLike(tipoOperacion);
        if (auditorias.isEmpty()) {
            throw new ResourceBadRequestException(String.format("No Auditoria found by type of operation %s", tipoOperacion));
        }
        return toDto(auditorias);
    }

    @Override
    public List<AuditoriaDto> findByFechaBetween(Instant fechaInicial, Instant fechaFinal) {
        List<Auditoria> auditorias = auditoriaRepository.findAuditoriasByFechaBetween(fechaInicial, fechaFinal);
        if (auditorias.isEmpty()) {
            // Formatea las fechas para el mensaje
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    .withZone(ZoneId.systemDefault());
            String fechaInicialStr = dateFormatter.format(fechaInicial);
            String fechaFinalStr = dateFormatter.format(fechaFinal);
            log.warn(String.format("no audit found between date range %s and %s", fechaInicialStr, fechaFinalStr));
            throw new ResourceNotFoundException(String.format("no audit found between date range %s and %s", fechaInicialStr, fechaFinalStr));
        }
        return toDto(auditorias);
    }

    @Override
    public List<AuditoriaDto> findByTabla(String tabla) {
        List<Auditoria> auditorias = auditoriaRepository.findAuditoriasByTablaLike(tabla);
        if (auditorias.isEmpty()) {
            log.warn(String.format("No Auditoria found by table %s", tabla));
            throw new ResourceBadRequestException(String.format("No Auditoria found by table %s", tabla));

        }
        return toDto(auditorias);
    }

    @Override
    public List<AuditoriaDto> findByUsuario(String usuario) {
        List<Auditoria> auditorias = auditoriaRepository.findAuditoriasByUsuarioContaining(usuario);
        if (auditorias.isEmpty()) {
            log.warn(String.format("No Auditoria found by usuario %s", usuario));
            throw new ResourceBadRequestException(String.format("No Auditoria found by usuario %s", usuario));
        }
        return toDto(auditorias);
    }
}
