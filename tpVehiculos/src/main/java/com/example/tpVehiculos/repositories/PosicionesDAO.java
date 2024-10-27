package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Posiciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// VehiclePositionRepository.java
@Repository
public interface PosicionesDAO extends JpaRepository<Posiciones, Long> {}
