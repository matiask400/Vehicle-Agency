package com.example.notificaciones.controller.DTO;

import com.example.notificaciones.models.Pruebas;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DTOPruebas {
    private Long id;
    private Long idVehiculo;
    private Long idInteresado;
    private Long idEmpleado;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private String comentarios;


    public DTOPruebas(Pruebas pruebas) {
        this.id = pruebas.getId();
        this.idVehiculo = pruebas.getVehiculo().getId();
        this.idInteresado = pruebas.getInteresado().getId();
        this.idEmpleado = pruebas.getEmpleado().getLegajo();
        this.horaInicio = pruebas.getFechaHoraInicio();
        this.horaFin = pruebas.getFechaHoraFin();
        this.comentarios = pruebas.getComentarios();
    }
}
