package ar.edu.utn.frc.bda.repositories;

import ar.edu.utn.frc.bda.models.Posiciones;
import ar.edu.utn.frc.bda.services.ConfiguracionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PosicionesCustomDAO {
    @PersistenceContext
    private EntityManager em;
    private final ConfiguracionService configuracionService;

    @Transactional
    public Posiciones savePosicion(Posiciones posicion) {
        em.persist(posicion);
        return posicion;
    }

    // Obtener posiciones de un vehículo en un rango de fechas
    public List<Posiciones> obtenerPosiciones(Long idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        String query = "SELECT p FROM Posicion p WHERE p.vehiculo.id = :idVehiculo " +
                "AND p.fechaHora BETWEEN :fechaInicio AND :fechaFin";
        return em.createQuery(query, Posiciones.class)
                .setParameter("idVehiculo", idVehiculo)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
    }

    // Calcular distancia total recorrida por un vehículo
    public Double calcularDistanciaTotal(Long idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Posiciones> posiciones = obtenerPosiciones(idVehiculo, fechaInicio, fechaFin);

        if (posiciones == null || posiciones.isEmpty()) return 0.0;

        // Coordenadas iniciales de la agencia
        var configuracion = configuracionService.obtenerConfiguracion();
        double latActual = configuracion.getCoordenadasAgencia().getLat();
        double lonActual = configuracion.getCoordenadasAgencia().getLon();

        double distanciaTotal = 0.0;

        for (Posiciones posicion : posiciones) {
            double latNueva = posicion.getLatitud();
            double lonNueva = posicion.getLongitud();

            // Calcula la distancia entre dos puntos geográficos
            distanciaTotal += calcularDistancia(latActual, lonActual, latNueva, lonNueva);

            // Actualiza las coordenadas actuales
            latActual = latNueva;
            lonActual = lonNueva;
        }

        return distanciaTotal;
    }

    // Fórmula de Haversine para calcular la distancia entre dos puntos geográficos
    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        final int RADIO_TIERRA = 6371; // Radio de la Tierra en kilómetros

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA * c;
    }
}
