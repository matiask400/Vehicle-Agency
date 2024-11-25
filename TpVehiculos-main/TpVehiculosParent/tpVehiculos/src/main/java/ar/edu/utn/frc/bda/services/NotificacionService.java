package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Notificaciones;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    private static final String CONFIG_URL = "http://localhost:8082/api/notificaciones/advertencia";

    public void enviarNotificacion(Notificaciones notificacion) {
        try {
            // Crear una nueva instancia de RestTemplate
            RestTemplate restTemplate = new RestTemplate();

            // Enviar la notificación y obtener la respuesta
            ResponseEntity<Void> response = restTemplate.postForEntity(CONFIG_URL, notificacion, Void.class);

            // Verificar el código de estado de la respuesta
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Notificación enviada exitosamente.");
            } else {
                System.err.println("Error al enviar la notificación. Código de respuesta: " + response.getStatusCode());
            }

        } catch (Exception e) {
            // Imprimir la traza completa del error
            e.printStackTrace();
            System.err.println("Error al enviar la notificación: " + e.getMessage());
        }
    }
}
