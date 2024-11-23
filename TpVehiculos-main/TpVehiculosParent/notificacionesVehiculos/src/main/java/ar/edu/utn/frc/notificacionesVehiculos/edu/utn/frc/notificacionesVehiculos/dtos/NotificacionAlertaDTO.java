package ar.edu.utn.frc.notificacionesVehiculos.edu.utn.frc.notificacionesVehiculos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificacionAlertaDTO {
    private String motivo;
    private String mensaje;
    private int vehiculoId;
}