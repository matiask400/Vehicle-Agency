package ar.edu.utn.frc.bda.config;

import lombok.Data;

@Data
public class ZonaRestringida {
    private Coordenadas noroeste;
    private Coordenadas sureste;

}