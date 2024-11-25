package com.example.tpVehiculos.controller.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DTOVehiculo {
    private Integer id;
    private String patente;
    private org.example.tpi_134.DTOS.DTOReporte dtoReporte;
}
