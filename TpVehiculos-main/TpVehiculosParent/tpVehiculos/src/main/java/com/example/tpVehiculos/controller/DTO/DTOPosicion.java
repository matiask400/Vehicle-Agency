package com.example.tpVehiculos.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPosicion {

    private Long vehiculoId;    // Identificador del vehículo
    private double latitud;     // Latitud de la posición del vehículo
    private double longitud;    // Longitud de la posición del vehículo
}

