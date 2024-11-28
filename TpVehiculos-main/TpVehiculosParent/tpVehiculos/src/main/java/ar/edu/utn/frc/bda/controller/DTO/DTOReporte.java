package ar.edu.utn.frc.bda.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DTOReporte {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
