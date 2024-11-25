package ar.edu.utn.frc.bda.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPosicion {

    private Long vehiculoId;    // Identificador del vehículo
    private Double latitud;     // Latitud de la posición del vehículo
    private Double longitud;    // Longitud de la posición del vehículo
}

