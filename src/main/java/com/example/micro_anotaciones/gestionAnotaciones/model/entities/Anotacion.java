package com.example.micro_anotaciones.gestionAnotaciones.model.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "anotaciones")
public class Anotacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anotacion")
    private Integer idAnotacion;
    
    @Column(name = "tipo_anotacion", nullable = false)
    private String tipoAnotacion;

    @Column(name = "descripcion_hechos", nullable = false)
    private String descripcionHechos;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "nivel_gravedad", nullable = false)
    private String nivelGravedad;

}
