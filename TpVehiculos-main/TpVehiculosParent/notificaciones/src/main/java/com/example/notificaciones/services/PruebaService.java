package com.example.notificaciones.services;

import com.example.notificaciones.controller.DTO.DTOConfiguracion;
import org.springframework.stereotype.Service;

@Service
public class PruebaService {

    private final ConfiguracionService configuracionService;

    public PruebaService(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    public boolean verificarPosicion(PosicionDTO posicionDTO) {
        // Obtener configuración desde la API
        DTOConfiguracion configuracion = configuracionService.obtenerConfiguracion();

        // Calcular distancia al centro de la agencia
        double distancia = calcularDistanciaEuclidea(
                configuracion.getCoordenadasAgencia().getLat(),
                configuracion.getCoordenadasAgencia().getLon(),
                posicionDTO.getLatitud(),
                posicionDTO.getLongitud()
        );

        if (distancia > configuracion.getRadioAdmitidoKm()) {
            return false;
        }

        // Verificar zonas restringidas
        for (DTOConfiguracion.ZonaRestringida zona : configuracion.getZonasRestringidas()) {
            if (estaDentroDeZonaPeligrosa(posicionDTO, zona)) {
                return false;
            }
        }

        return true;
    }

    private boolean estaDentroDeZonaPeligrosa(PosicionDTO posicion, Configuracion.ZonaRestringida zona) {
        return posicion.getLatitud() >= zona.getNoroeste().getLat()
                && posicion.getLatitud() <= zona.getSureste().getLat()
                && posicion.getLongitud() >= zona.getNoroeste().getLon()
                && posicion.getLongitud() <= zona.getSureste().getLon();
    }
}