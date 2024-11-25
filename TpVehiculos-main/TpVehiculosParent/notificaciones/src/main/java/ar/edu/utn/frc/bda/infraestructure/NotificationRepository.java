package ar.edu.utn.frc.bda.infraestructure;

import ar.edu.utn.frc.bda.domain.Notificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificaciones")

public interface NotificationRepository extends CrudRepository<Notificacion, Long> {

}
