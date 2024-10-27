package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Notificaciones;
import com.example.tpVehiculos.models.Posiciones;
import com.example.tpVehiculos.repositories.PosicionesDAO;
import com.example.tpVehiculos.repositories.NotificacionesDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PosicionesService {

    private final PosicionesDAO posicionesRepository;
    private final NotificacionesDAO notificationRepository;

    public PosicionesService(PosicionesDAO posicionesRepository, NotificacionesDAO notificationRepository) {
        this.posicionesRepository = posicionesRepository;
        this.notificationRepository = notificationRepository;
    }

    public void evaluarPosicion(Posiciones posicionActual, double radioPermitido, double zonaPeligrosaLat, double zonaPeligrosaLon) {
        if (!estaDentroDelRadio(posicionActual, radioPermitido)) {
            generarNotificacion("El vehículo ha salido del radio permitido.");
        }

        if (estaEnZonaPeligrosa(posicionActual, zonaPeligrosaLat, zonaPeligrosaLon)) {
            generarNotificacion("El vehículo ha ingresado a una zona peligrosa.");
        }

        posicionesRepository.save(posicionActual);  // Guardar la posición actual
    }

    private boolean estaDentroDelRadio(Posiciones posicion, double radioPermitido) {
        // Aquí iría la lógica para validar el radio, por ejemplo usando la distancia entre coordenadas.
        return true;  // Solo para el ejemplo, debes calcular la distancia.
    }

    private boolean estaEnZonaPeligrosa(Posiciones posicion, double lat, double lon) {
        // Aquí iría la lógica para verificar si las coordenadas corresponden a una zona peligrosa.
        return false;  // Modifica según tu lógica de zona peligrosa.
    }

    private void generarNotificacion(String mensaje) {
        Notificaciones notificacion = new Notificaciones();
        notificacion.setMessage(mensaje);
        notificacion.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notificacion);
    }
}
