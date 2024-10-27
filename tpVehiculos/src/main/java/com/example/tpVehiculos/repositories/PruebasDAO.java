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

    // Consultar pruebas activas por vehículo
    @Query("SELECT p FROM Pruebas p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHoraFin IS NULL")
    Optional<Pruebas> findPruebaActivaByVehiculo(@Param("idVehiculo") Long idVehiculo);

    // Consultar todas las pruebas con exceso de límite
    List<Pruebas> findByExcesoLimite(boolean excesoLimite);

    // Consultar incidentes para un empleado específico usando la convención correcta
    List<Pruebas> findByEmpleado_IdAndExcesoLimite(Long empleadoId, boolean excesoLimite);

    // Consultar pruebas en curso en un momento específico
    @Query("SELECT p FROM Pruebas p WHERE p.fechaHoraInicio <= :fechaHora AND (p.fechaHoraFin IS NULL OR p.fechaHoraFin > :fechaHora)")
    List<Pruebas> findPruebasEnCurso(@Param("fechaHora") LocalDateTime fechaHora);

    // Consultar todas las pruebas realizadas por un vehículo
    List<Pruebas> findByVehiculoId(Long idVehiculo);

    List<Pruebas> findByEmpleadoIdAndExcesoLimite(Long idEmpleado, boolean b);
}
