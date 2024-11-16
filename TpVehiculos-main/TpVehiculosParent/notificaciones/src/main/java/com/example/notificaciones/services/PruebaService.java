package com.example.notificaciones.services;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.models.Interesados;
import com.example.tpVehiculos.models.Vehiculos;
import com.example.tpVehiculos.models.Empleados;
import com.example.tpVehiculos.repositories.PruebasDAO;
import com.example.tpVehiculos.repositories.InteresadoDAO;
import com.example.tpVehiculos.repositories.VehiculosDAO;
import com.example.tpVehiculos.repositories.EmpleadosDAO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {

    private final ConfiguracionService configuracionService;

    public PruebaService(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    public boolean verificarPosicion(PosicionDTO posicionDTO) {
        // Obtener configuración desde la API
        Configuracion configuracion = configuracionService.obtenerConfiguracion();

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
        for (Configuracion.ZonaRestringida zona : configuracion.getZonasRestringidas()) {
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