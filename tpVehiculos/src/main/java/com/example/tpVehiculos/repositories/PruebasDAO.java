package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Pruebas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PruebasDAO extends JpaRepository<Pruebas, Long> {

    @Query("SELECT p FROM Pruebas p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHoraFin IS NULL")
    Optional<Pruebas> findPruebaActivaByVehiculo(@Param("idVehiculo") Long idVehiculo);

    // New query to find all ongoing tests at a specific time
    @Query("SELECT p FROM Pruebas p WHERE p.fechaHoraInicio <= :fechaHora AND (p.fechaHoraFin IS NULL OR p.fechaHoraFin > :fechaHora)")
    List<Pruebas> findPruebasEnCurso(@Param("fechaHora") LocalDateTime fechaHora);
}
