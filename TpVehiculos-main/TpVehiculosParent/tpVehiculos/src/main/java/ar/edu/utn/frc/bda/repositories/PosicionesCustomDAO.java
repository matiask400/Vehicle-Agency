package ar.edu.utn.frc.bda.repositories;

import ar.edu.utn.frc.bda.client.ApiClient;
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

    // Obtener posiciones de un vehículo en un rango de fechas

    public List<Posiciones> obtenerPosiciones(Long idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        String query = "SELECT p FROM Posiciones p WHERE p.vehiculo.id = :idVehiculo " +
                "AND p.fechaHora BETWEEN :fechaInicio AND :fechaFin";
        return em.createQuery(query, Posiciones.class)
                .setParameter("idVehiculo", idVehiculo)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
    }

    // Calcular distancia total recorrida por un vehículo
    private boolean calcularDistanciaTotal(double latVehiculo, double lonVehiculo) {
        DTOAgencia agencia = obtenerInformacionAgencia();
        double latitudAgencia = agencia.getCoordenadasAgencia().getLat();
        double longitudAgencia = agencia.getCoordenadasAgencia().getLon();
        int radioPermitido = agencia.getRadioAdmintido();

        double distancia = Math.sqrt(Math.pow(latVehiculo - latitudAgencia, 2) + Math.pow(lonVehiculo - longitudAgencia, 2));
        System.out.println("distanca" + distancia + "ra" + radioPermitido);
        return distancia < radioPermitido;
    }



    // Fórmula de Haversine para calcular la distancia entre dos puntos geográficos
    public boolean calcularDistancia(double latVehiculo, double lonVehiculo) {
        DTOAgencia agencia = obtenerInformacionAgencia();
        double latitud =  agencia.getCoordenadasAgencia().getLat();
        double longitud = agencia.getCoordenadasAgencia().getLon();
        int radioPermitido = agencia.getRadioAdmintido();

        double lat1Rad = Math.toRadians(latVehiculo);
        double lon1Rad = Math.toRadians(lonVehiculo);
        double lat2Rad = Math.toRadians(latitud);
        double lon2Rad = Math.toRadians(longitud);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = 6371 * c;

        return distance < radioPermitido;
    }
    private DTOAgencia obtenerInformacionAgencia() {
        ApiClient apiClient = new ApiClient();
        return apiClient.getAgenciaInfo();
    }
}
