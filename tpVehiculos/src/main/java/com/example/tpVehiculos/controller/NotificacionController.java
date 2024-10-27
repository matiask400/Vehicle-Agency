package com.example.tpVehiculos.controller;


import com.example.tpVehiculos.models.Notificaciones;
import com.example.tpVehiculos.repositories.NotificacionesDAO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionesDAO notificationRepository;

    @GetMapping
    public List<Notificaciones> obtenerNotificaciones() {
        return notificationRepository.findAll();
    }
}

