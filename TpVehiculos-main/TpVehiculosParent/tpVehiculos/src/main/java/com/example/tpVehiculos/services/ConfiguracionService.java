package com.example.tpVehiculos.services;

import com.example.tpVehiculos.config.Configuracion;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConfiguracionService {

    private static final String CONFIG_URL = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";

    public Configuracion obtenerConfiguracion() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(CONFIG_URL, Configuracion.class);
    }
}