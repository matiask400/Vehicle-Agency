package com.example.notificaciones.repositories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionDTO {

    private Coordenadas coordenadasAgencia;
    private double radioAdmitidoKm;
    private List<ZonaRestringida> zonasRestringidas;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coordenadas {
        private double lat;
        private double lon;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ZonaRestringida {
        private Coordenadas noroeste;
        private Coordenadas sureste;
    }
}
