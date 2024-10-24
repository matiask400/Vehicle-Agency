package com.example.tpVehiculos.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    boolean existsByVehiculoAndFechaFinIsNull(Vehiculo vehiculo);
}
