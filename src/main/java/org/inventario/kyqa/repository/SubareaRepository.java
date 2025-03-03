package org.inventario.kyqa.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.inventario.kyqa.entities.Subarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface SubareaRepository extends JpaRepository<Subarea, Integer> {

    @Transactional(readOnly = true)
    Subarea findByNombre(@NotBlank @Size(max = 100) String nombre);

    @Transactional(readOnly = true)
    List<Subarea> findSubareasByNombreContaining(@NotBlank @Size(max = 100) String nombre);

    @Transactional(readOnly = true)
    Boolean existsByNombre(@NotBlank @Size(max = 100) String nombre);

    @Transactional
    void deleteByNombre(@NotBlank @Size(max = 100) String nombre);
}