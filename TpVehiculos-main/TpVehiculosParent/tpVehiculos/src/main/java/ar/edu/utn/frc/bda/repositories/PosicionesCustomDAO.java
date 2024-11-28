package ar.edu.utn.frc.bda.repositories;

import ar.edu.utn.frc.bda.client.ApiClient;
import ar.edu.utn.frc.bda.config.Configuracion;
import ar.edu.utn.frc.bda.controller.DTO.DTOAgencia;
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

    public Double calcularDistanciaTotal(Long idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin ) {
        List<Posiciones> posiciones = obtenerPosiciones(idVehiculo,fechaInicio,fechaFin);
        System.out.println("POSCIONES" + posiciones);

        if (posiciones == null || posiciones.isEmpty()) {
            return 0.0;
        }

        // Coordenadas iniciales de la agencia
        Configuracion configuracion = configuracionService.obtenerConfiguracion();
        double latActual = configuracion.getCoordenadasAgencia().getLat();
        double lonActual = configuracion.getCoordenadasAgencia().getLon();

        double distanciaTotal = 0.0;

        for (Posiciones posicion : posiciones) {
            double latNueva = posicion.getLatitud();
            double lonNueva = posicion.getLongitud();

            // Calcula la distancia entre la posición actual y la nueva
            double distancia = calcularDistancia(latActual, lonActual, latNueva, lonActual);
            System.out.println(distancia);

            // Suma la distancia al total
            distanciaTotal += distancia;

            // Actualiza las coordenadas actuales para la siguiente iteración
            latActual = latNueva;
            lonActual = lonNueva;
        }

        return distanciaTotal;
    }


    // Vehiculo y un tiempo determinado
    public List<Posiciones> obtenerPosiciones(Long idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            // Consulta JPQL para obtener las posiciones del vehículo en el rango de fechas
            String query = "SELECT p FROM Posiciones p WHERE p.vehiculo.id = :idVehiculo " +
                    "AND p.fechaHora BETWEEN :fechaInicio AND :fechaFin";

            return em.createQuery(query, Posiciones.class)
                    .setParameter("idVehiculo", idVehiculo)
                    .setParameter("fechaInicio", fechaInicio)
                    .setParameter("fechaFin", fechaFin)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Distancia euclídea en un plano
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        return Math.sqrt(dLat * dLat + dLon * dLon);
    }


    private DTOAgencia obtenerInformacionAgencia() {
        ApiClient apiClient = new ApiClient();
        return apiClient.getAgenciaInfo();
    }
}