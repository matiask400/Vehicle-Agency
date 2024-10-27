package com.example.tpVehiculos.services;

import com.example.tpVehiculos.repositories.NotificacionesDAO;
import com.example.tpVehiculos.repositories.PosicionesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleService {

    private final PosicionesDAO vehiclePositionRepository;
    private final NotificacionesDAO notificationRepository;

    // Establece el radio permitido y las coordenadas de la zona peligrosa
    private static final double ALLOWED_RADIUS = 1000; // en metros
    private static final double DANGER_ZONE_LATITUDE = -31.42;
    private static final double DANGER_ZONE_LONGITUDE = -64.18;
    private static final double DANGER_ZONE_RADIUS = 500; // en metros

    @Autowired
    public VehicleService(PosicionesDAO vehiclePositionRepository, NotificacionesDAO notificationRepository) {
        this.vehiclePositionRepository = vehiclePositionRepository;
        this.notificationRepository = notificationRepository;
    }

    public void processVehiclePosition(Posiciones posicion) {
        vehiclePositionRepository.save(posicion);

        if (distanceToCenter > ALLOWED_RADIUS) {
            double distanceToCenter = calculateDistance(posicion.getLatitude(), posicion.getLongitude(), -31.42, -64.18); // centro de prueba
            saveNotification("El vehículo ha excedido el radio permitido.");
        }

        double distanceToDangerZone = calculateDistance(posicion.getLatitude(), posicion.getLongitude(), DANGER_ZONE_LATITUDE, DANGER_ZONE_LONGITUDE);
        if (distanceToDangerZone < DANGER_ZONE_RADIUS) {
            saveNotification("El vehículo ha ingresado a una zona peligrosa.");
        }
    }

    private void saveNotification(String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371e3; // en metros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}
