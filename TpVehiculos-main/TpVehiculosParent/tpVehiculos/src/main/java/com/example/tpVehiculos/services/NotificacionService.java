package com.example.tpVehiculos.services;


import com.example.tpVehiculos.models.Notificaciones;
import com.example.tpVehiculos.repositories.NotificacionesDAO;
import com.example.tpVehiculos.repositories.NotificacionesDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionesDAO notificationRepository;

    public void enviarPromocion(String mensaje, List<String> telefonos) {
        telefonos.forEach(telefono -> {
            Notificaciones notificacion = new Notificaciones(
                    null, mensaje, LocalDateTime.now(), telefono);
            notificationRepository.save(notificacion);
        });
    }
}
