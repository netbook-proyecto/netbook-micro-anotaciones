package com.example.micro_anotaciones.gestionAnotaciones.service;

import com.example.micro_anotaciones.gestionAnotaciones.model.entities.Anotacion;
import com.example.micro_anotaciones.gestionAnotaciones.model.requests.ActualizarAnotacionRequest;
import com.example.micro_anotaciones.gestionAnotaciones.model.requests.RegistrarAnotacionRequest;
import com.example.micro_anotaciones.gestionAnotaciones.repository.AnotacionRepository;
import com.example.micro_anotaciones.gestionAnotaciones.model.dtos.EstudianteDTO; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnotacionService {

    @Autowired
    private AnotacionRepository repository;

    @Autowired
    private WebClient estudiantesWebClient;

    public List<Anotacion> obtenerTodos() {
        return repository.findAll();
    }

    public Anotacion obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotación no encontrada"));
    }

    public Anotacion registrar(RegistrarAnotacionRequest request) {
        try {
            EstudianteDTO estudiante = estudiantesWebClient.get()
                    .uri("/estudiantes/" + request.getIdEstudiante()) 
                    .retrieve()
                    .bodyToMono(EstudianteDTO.class)
                    .block(); 
            
            System.out.println("Estudiante validado con éxito: " + estudiante.getNombres() + " " + estudiante.getApellidoPaterno());

        } catch (WebClientResponseException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: El estudiante con ID " + request.getIdEstudiante() + " no existe en el sistema.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo conectar con el servicio de estudiantes. Intente más tarde.");
        }

        Anotacion anotacion = new Anotacion();
        anotacion.setIdEstudiante(request.getIdEstudiante()); 
        anotacion.setTipoAnotacion(request.getTipoAnotacion());
        anotacion.setDescripcionHechos(request.getDescripcionHechos());
        anotacion.setNivelGravedad(request.getNivelGravedad());
        anotacion.setFechaRegistro(LocalDate.now()); 
        
        return repository.save(anotacion);
    }

    public Anotacion actualizar(Integer id, ActualizarAnotacionRequest request) {
        Anotacion anotacion = obtenerPorId(id);
        anotacion.setTipoAnotacion(request.getTipoAnotacion());
        anotacion.setDescripcionHechos(request.getDescripcionHechos());
        anotacion.setNivelGravedad(request.getNivelGravedad());
        
        return repository.save(anotacion);
    }

    public void eliminar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotación no encontrada");
        }
        repository.deleteById(id);
    }   
}