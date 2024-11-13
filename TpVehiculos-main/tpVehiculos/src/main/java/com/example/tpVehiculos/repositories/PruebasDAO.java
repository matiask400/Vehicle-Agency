package com.example.tpVehiculos.repositories;

import com.example.tpVehiculos.models.Pruebas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PruebasDAO extends JpaRepository<Pruebas, Long> {

    // Consultar pruebas activas por vehículo
    @Query("SELECT p FROM Pruebas p WHERE p.vehiculo.id = :idVehiculo AND p.fechaHoraFin IS NULL")
    Optional<Pruebas> findPruebaActivaByVehiculo(@Param("idVehiculo") Long idVehiculo);

    // Consultar pruebas en curso en un momento específico
    @Query("SELECT p FROM Pruebas p WHERE p.fechaHoraInicio <= :fechaHora AND (p.fechaHoraFin IS NULL OR p.fechaHoraFin > :fechaHora)")
    List<Pruebas> findPruebasEnCurso(@Param("fechaHora") String fechaHora);

    // Modificado para que funcione con el atributo 'legajo' de Empleado correctamente
    @Query("SELECT p FROM Pruebas p WHERE p.empleado.legajo = :legajoEmpleado")
    List<Pruebas> findByEmpleadoLegajo(@Param("legajoEmpleado") Long legajoEmpleado);

    List<Pruebas> findByVehiculoId(Long idVehiculo);

    List<Pruebas> findAllByFechaHoraInicioBeforeAndFechaHoraFinIsNull(@Param("fechaHora") String fechaHora);
}
