package com.example.notificaciones.services;

import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfiguracionService {

    private final RestTemplate restTemplate;

    @Value("${api.configuracion.url}")
    private String apiUrl;

    public ConfiguracionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Configuracion obtenerConfiguracion() {
        ResponseEntity<Configuracion> response = restTemplate.getForEntity(apiUrl, Configuracion.class);
        return response.getBody();
    }
}
