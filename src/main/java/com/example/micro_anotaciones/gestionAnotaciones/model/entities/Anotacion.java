package com.example.micro_anotaciones.gestionAnotaciones.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "anotaciones")
@Data
public class Anotacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;

    @Column(name = "tipo_anotacion", nullable = false)
    private String tipoAnotacion;

    @Column(name = "descripcion_hechos", nullable = false, length = 500)
    private String descripcionHechos;

    @Column(name = "nivel_gravedad", nullable = false)
    private String nivelGravedad;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;
}