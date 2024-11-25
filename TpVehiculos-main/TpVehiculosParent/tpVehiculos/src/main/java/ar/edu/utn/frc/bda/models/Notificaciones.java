package com.example.tpVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Notificaciones {

    private String mensaje;
    private String tipo;

}

