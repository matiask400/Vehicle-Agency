package ar.edu.utn.frc.bda.controller.DTO;


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
    private DTOReporte dtoReporte;
}
