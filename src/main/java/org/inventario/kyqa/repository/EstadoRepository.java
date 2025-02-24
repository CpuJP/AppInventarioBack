package org.inventario.kyqa.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.inventario.kyqa.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    @Transactional(readOnly = true)
    Estado findByNombre(@NotBlank @Size(max = 100) String nombre);

    @Transactional(readOnly = true)
    List<Estado> findEstadosByNombreContaining(@NotBlank @Size(max = 100) String nombre);

    @Transactional(readOnly = true)
    Boolean existsByNombre(@NotBlank @Size(max = 100) String nombre);

    @Transactional
    void deleteByNombre(@NotBlank @Size(max = 100) String nombre);
}