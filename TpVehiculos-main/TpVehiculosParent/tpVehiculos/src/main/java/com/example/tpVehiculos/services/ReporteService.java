package com.example.tpVehiculos.services;

import com.example.tpVehiculos.models.Pruebas;
import com.example.tpVehiculos.models.Posiciones;
import com.example.tpVehiculos.repositories.PruebasDAO;
import com.example.tpVehiculos.repositories.PosicionesDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReporteService {

    private final PruebasDAO pruebasDAO;
    private final PosicionesDAO posicionesDAO;

    public ReporteService(PruebasDAO pruebasDAO, PosicionesDAO posicionesDAO) {
        this.pruebasDAO = pruebasDAO;
        this.posicionesDAO = posicionesDAO;
    }

    // i. Reporte de incidentes (pruebas donde se excedieron los límites establecidos)
    public List<Pruebas> obtenerIncidentes() {
        return pruebasDAO.findByExcesoLimite(true);
    }

    // ii. Detalle de incidentes para un empleado (pruebas donde se excedieron los límites establecidos)
    public List<Pruebas> obtenerIncidentesPorEmpleado(Long legajoEmpleado) {
        return pruebasDAO.findByEmpleadoLegajoAndExcesoLimite(legajoEmpleado, true);
    }

    // iii. Cantidad de kilómetros de prueba que recorrió un vehículo en un período
    public double obtenerKilometrosRecorridos(Long idVehiculo, LocalDateTime inicio, LocalDateTime fin) {
        List<Posiciones> posiciones = posicionesDAO.findByVehiculoIdAndFechaHoraBetween(idVehiculo, inicio, fin);

        double distanciaTotal = 0.0;
        for (int i = 1; i < posiciones.size(); i++) {
            Posiciones anterior = posiciones.get(i - 1);
            Posiciones actual = posiciones.get(i);
            distanciaTotal += calcularDistancia(
                    anterior.getLatitud(), anterior.getLongitud(),
                    actual.getLatitud(), actual.getLongitud()
            );
        }
        return distanciaTotal;
    }

    // iv. Detalle de pruebas realizadas para un vehículo
    public List<Pruebas> obtenerPruebasPorVehiculo(Long idVehiculo) {
        return pruebasDAO.findByVehiculoId(idVehiculo);
    }

    // Método para calcular distancia entre dos puntos (Haversine)
    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon1 - lon2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
