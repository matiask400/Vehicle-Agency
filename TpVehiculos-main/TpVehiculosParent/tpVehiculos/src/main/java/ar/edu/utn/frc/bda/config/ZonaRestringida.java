package ar.edu.utn.frc.bda.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ZonaRestringida {
    private Coordenadas noroeste;
    private Coordenadas sureste;

}