package org.inventario.kyqa.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

@Entity
@Table(name = "vista_inventarios_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VistaInventariosItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_item", updatable = false, insertable = false)
    private Integer numeroItem;

    @Column(name = "inventario_nombre", length = 255, nullable = false)
    private String inventarioNombre;

    @Column(name = "estado_item", length = 100, nullable = false)
    private String estadoItem;

    @Column(name = "item_nombre", length = 255, nullable = false)
    private String itemNombre;

    @Column(name = "subareas", columnDefinition = "TEXT")
    private String subAreas;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "ultima_actualizacion")
    private Instant ultimaActualizacion;

    @Column(name = "foto", columnDefinition = "TEXT")
    private String foto;
}
