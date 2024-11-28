package ar.edu.utn.frc.bda.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Notificacion {

    private String mensaje;
    private String tipo;
}
