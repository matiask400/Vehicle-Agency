package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosDAO extends JpaRepository<Empleados, Long> {
    // No es necesario agregar métodos adicionales aquí por ahora,
    // ya que JpaRepository proporciona los métodos básicos como findById()
}
