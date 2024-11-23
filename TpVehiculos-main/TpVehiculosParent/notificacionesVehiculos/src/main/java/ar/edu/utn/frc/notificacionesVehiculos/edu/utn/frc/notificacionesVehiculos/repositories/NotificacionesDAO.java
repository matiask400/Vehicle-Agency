package ar.edu.utn.frc.notificacionesVehiculos.edu.utn.frc.notificacionesVehiculos.repositories;

import com.example.tpVehiculos.models.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionesDAO extends JpaRepository<Notificaciones, Long> {
}
