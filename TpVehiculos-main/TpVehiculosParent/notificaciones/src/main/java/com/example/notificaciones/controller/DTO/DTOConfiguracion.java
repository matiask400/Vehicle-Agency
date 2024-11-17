package com.example.notificaciones.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOConfiguracion {

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
