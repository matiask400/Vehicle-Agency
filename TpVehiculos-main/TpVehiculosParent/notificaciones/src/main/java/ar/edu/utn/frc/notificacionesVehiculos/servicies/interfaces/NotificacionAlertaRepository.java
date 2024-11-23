package ar.edu.utn.frc.notificacionesVehiculos.servicies.interfaces;

import ar.edu.utn.frc.notificacionesVehiculos.models.NotificacionAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionAlertaRepository extends JpaRepository<NotificacionAlerta, Integer> {
}