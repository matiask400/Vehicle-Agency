package ar.edu.utn.frc.bda.controller.DTO;

import ar.edu.utn.frc.bda.config.Coordenadas;
import ar.edu.utn.frc.bda.config.ZonaRestringida;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class DTOAgencia {
    private Coordenadas coordenadasAgencia;
    private int radioAdmitido;
    private List<ZonaRestringida> zonasRestringidas;
}
