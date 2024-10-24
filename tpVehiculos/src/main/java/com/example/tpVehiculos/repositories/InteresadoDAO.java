package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Interesados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresadoDAO extends JpaRepository<Interesados, Long> {
    // No es necesario agregar métodos personalizados por ahora,
    // ya que JpaRepository proporciona los métodos básicos como findById()
}

