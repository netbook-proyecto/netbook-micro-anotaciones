package com.example.micro_anotaciones.gestionAnotaciones.model.dtos;

import lombok.Data;

@Data
public class EstudianteDTO {
    private Long idEstudiante;
    private String rut;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
}
