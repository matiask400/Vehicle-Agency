package com.example.tpVehiculos.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    boolean existsByEnPruebaTrueAndPatente(String patente);
}
