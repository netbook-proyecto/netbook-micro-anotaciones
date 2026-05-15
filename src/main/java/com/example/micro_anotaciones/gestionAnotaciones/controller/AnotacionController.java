package com.example.micro_anotaciones.gestionAnotaciones.controller;

import com.example.micro_anotaciones.gestionAnotaciones.model.entities.Anotacion;
import com.example.micro_anotaciones.gestionAnotaciones.model.requests.ActualizarAnotacionRequest;
import com.example.micro_anotaciones.gestionAnotaciones.model.requests.RegistrarAnotacionRequest;
import com.example.micro_anotaciones.gestionAnotaciones.service.AnotacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/anotaciones")
public class AnotacionController {
    
    @Autowired
    private AnotacionService anotacionService;

    @GetMapping 
    public List<Anotacion> obtenerTodasLasAnotaciones(){
        return anotacionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Anotacion obtenerAnotacionPorId(@PathVariable Integer id){
        return anotacionService.obtenerPorId(id);
    }

    @PostMapping
    public Anotacion registrarAnotacion(@RequestBody RegistrarAnotacionRequest request){
        return anotacionService.registrar(request);
    }

    @PutMapping("/{id}")
    public Anotacion actualizarAnotacion(@PathVariable Integer id, @RequestBody ActualizarAnotacionRequest request){
        Anotacion anotacionExistente = anotacionService.obtenerPorId(id);
        if (anotacionExistente == null) {
            throw new RuntimeException("Anotación no encontrada");
        }
        return anotacionService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminarAnotacion(@PathVariable Integer id){
        anotacionService.eliminar(id);
    }
}