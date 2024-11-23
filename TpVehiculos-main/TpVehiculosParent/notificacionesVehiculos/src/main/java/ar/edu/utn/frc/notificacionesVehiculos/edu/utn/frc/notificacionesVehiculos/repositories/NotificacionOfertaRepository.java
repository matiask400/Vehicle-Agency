package ar.edu.utn.frc.notificacionesVehiculos.edu.utn.frc.notificacionesVehiculos.repositories;

import ar.edu.utn.frc.notificacionesVehiculos.edu.utn.frc.notificacionesVehiculos.models.NotificacionOferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionOfertaRepository extends JpaRepository<NotificacionOferta, Integer> {
}
