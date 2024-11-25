package ar.edu.utn.frc.bda.repositories;

import ar.edu.utn.frc.bda.models.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionesDAO extends JpaRepository<Notificaciones, Long> {
}
