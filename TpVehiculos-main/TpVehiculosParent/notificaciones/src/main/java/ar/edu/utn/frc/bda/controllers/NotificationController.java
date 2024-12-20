package ar.edu.utn.frc.bda.controllers;

import ar.edu.utn.frc.bda.DTOS.NotificacionDTO;
import ar.edu.utn.frc.bda.domain.Notificacion;
import ar.edu.utn.frc.bda.infraestructure.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/advertencia")
    public ResponseEntity<String> saveNotification(@RequestBody Notificacion notificacion) {
       try {
           notificationRepository.save(notificacion);
           return ResponseEntity.ok("Notificación guardada correctamente");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @PostMapping("/promocion")
    public ResponseEntity<String> saveNotification(@RequestBody NotificacionDTO notificaciondto) {
        try {
            String mensaje =  notificaciondto.getMensaje();
            String tipo= "PROMOCION";
            Notificacion notificacion = new Notificacion(mensaje,tipo);
            notificationRepository.save(notificacion);
            return ResponseEntity.ok("Notificación guardada correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}