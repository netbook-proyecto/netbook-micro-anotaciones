package com.example.micro_anotaciones.gestionAnotaciones.repository;

import com.example.micro_anotaciones.gestionAnotaciones.model.entities.Anotacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotacionRepository extends JpaRepository <Anotacion, Integer> {
}