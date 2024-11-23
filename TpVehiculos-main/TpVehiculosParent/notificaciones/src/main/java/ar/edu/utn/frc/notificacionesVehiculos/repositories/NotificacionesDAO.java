package ar.edu.utn.frc.notificacionesVehiculos.repositories;

import ar.edu.utn.frc.notificacionesVehiculos.models.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionesDAO extends JpaRepository<Notificaciones, Long> {
}
