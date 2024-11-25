package ar.edu.utn.frc.bda.repositories;

import ar.edu.utn.frc.bda.models.Posiciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PosicionesDAO extends JpaRepository<Posiciones, Long> {
    List<Posiciones> findByVehiculoIdOrderByFechaHoraDesc(Long vehiculoId);

    List<Posiciones> findByVehiculoIdAndFechaHoraBetween(Long vehiculoId, LocalDateTime inicio, LocalDateTime fin);
}