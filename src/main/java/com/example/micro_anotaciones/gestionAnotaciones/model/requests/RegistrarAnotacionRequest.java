package com.example.micro_anotaciones.gestionAnotaciones.model.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrarAnotacionRequest {
    
    @NotBlank(message = "El tipo de anotación es obligatorio")
    private String tipoAnotacion;

    @NotBlank(message = "La descripción de los hechos es obligatoria")
    private String descripcionHechos;

    @NotBlank(message = "El nivel de gravedad es obligatorio")
    private String nivelGravedad;
    
}
