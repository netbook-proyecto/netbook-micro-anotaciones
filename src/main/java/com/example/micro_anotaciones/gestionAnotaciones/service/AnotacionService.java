package com.example.micro_anotaciones.gestionAnotaciones.service;

import com.example.micro_anotaciones.gestionAnotaciones.model.entities.Anotacion;
import com.example.micro_anotaciones.gestionAnotaciones.model.requests.ActualizarAnotacionRequest;
import com.example.micro_anotaciones.gestionAnotaciones.model.requests.RegistrarAnotacionRequest;
import com.example.micro_anotaciones.gestionAnotaciones.repository.AnotacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnotacionService {

    @Autowired
    private AnotacionRepository repository;

    public List<Anotacion> obtenerTodos() {
        return repository.findAll();
    }

    public Anotacion obtenerPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anotación no encontrada"));
    }

    public Anotacion registrar(RegistrarAnotacionRequest request) {
        Anotacion anotacion = new Anotacion();
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