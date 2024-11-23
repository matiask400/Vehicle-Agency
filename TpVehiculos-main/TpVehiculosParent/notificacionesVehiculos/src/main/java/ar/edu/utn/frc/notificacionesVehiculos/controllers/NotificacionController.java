package ar.edu.utn.frc.notificacionesVehiculos.controllers;

import ar.edu.utn.frc.notificacionesVehiculos.dtos.NotificacionAlertaDTO;
import ar.edu.utn.frc.notificacionesVehiculos.models.NotificacionOferta;
import ar.edu.utn.frc.notificacionesVehiculos.servicies.NotificacionAlertaServiceImpl;
import ar.edu.utn.frc.notificacionesVehiculos.servicies.NotificacionOfertaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    private final NotificacionAlertaServiceImpl notificacionAlertaService;
    private final NotificacionOfertaServiceImpl notificacionOfertaService;

    public NotificacionController(NotificacionAlertaServiceImpl notificacionAlertaService, NotificacionOfertaServiceImpl notificacionOfertaService) {
        this.notificacionAlertaService = notificacionAlertaService;
        this.notificacionOfertaService = notificacionOfertaService;
    }

    @PostMapping
    public ResponseEntity<Object> recibirNotificacionAlerta(@RequestBody NotificacionAlertaDTO notificacion){
        try {
            notificacionAlertaService.crearNotificacion(notificacion);
            return new ResponseEntity<>(notificacion, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error al procesar la notificación.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear-oferta")
    public ResponseEntity<Object> crearNotificacionOferta(@RequestBody NotificacionOferta notificacion) {
        try {
            notificacionOfertaService.crearNotificacion(notificacion);
            return new ResponseEntity<>(notificacion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al procesar la notificación.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
