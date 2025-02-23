package org.inventario.kyqa.repository;

import org.inventario.kyqa.entities.VistaInventariosItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@Repository
public interface VistaInventariosItemsRepository extends JpaRepository<VistaInventariosItems, Integer> {
}