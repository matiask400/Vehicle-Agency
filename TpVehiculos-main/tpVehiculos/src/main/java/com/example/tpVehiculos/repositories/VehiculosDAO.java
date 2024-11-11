package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Vehiculos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculosDAO extends JpaRepository<Vehiculos, Long> {
    // No es necesario agregar métodos adicionales aquí por ahora,
    // ya que JpaRepository proporciona los métodos básicos como findById()
}
