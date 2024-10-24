package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Pruebas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PruebasDAO extends JpaRepository<Pruebas, Long> {

    @Query("SELECT p FROM Pruebas p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHoraFin IS NULL")
    Optional<Pruebas> findPruebaActivaByVehiculo(@Param("idVehiculo") Long idVehiculo);
}

