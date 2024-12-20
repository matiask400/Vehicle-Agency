package ar.edu.utn.frc.bda.controller;


import ar.edu.utn.frc.bda.models.Notificaciones;
import ar.edu.utn.frc.bda.repositories.NotificacionesDAO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionesDAO notificationRepository;

    @GetMapping
    public List<Notificaciones> obtenerNotificaciones() {
        return notificationRepository.findAll();
    }
}

