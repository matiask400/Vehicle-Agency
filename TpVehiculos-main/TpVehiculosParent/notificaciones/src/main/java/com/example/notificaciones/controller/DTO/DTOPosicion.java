package com.example.notificaciones.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPosicion {

    private String vehiculoId;  // Identificador del vehículo
    private double latitud;      // Latitud actual del vehículo
    private double longitud;     // Longitud actual del vehículo

}

