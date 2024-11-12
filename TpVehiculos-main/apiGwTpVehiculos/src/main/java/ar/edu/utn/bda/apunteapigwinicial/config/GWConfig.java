package ar.edu.utn.bda.apunteapigwinicial.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${apunte-api-gw-tpVehiculos.url-microservicio-vehiculos}") String uriVehiculos,
                                        @Value("${apunte-api-gw-tpVehiculos.url-microservicio-notificaciones}") String uriNotificaciones) {
        return builder.routes()
                // Ruteo al Microservicio de Personas
                .route(p -> p.path("/api/vehiculos/**").uri(uriVehiculos))
                // Ruteo al Microservicio de Entradas
                .route(p -> p.path("/api/notificaciones/**").uri(uriNotificaciones))
                .build();

    }

}
