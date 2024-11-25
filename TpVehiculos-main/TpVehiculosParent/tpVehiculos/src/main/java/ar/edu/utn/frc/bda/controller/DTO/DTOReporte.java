package org.example.tpi_134.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DTOReporte {
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
}
