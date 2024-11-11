package com.example.tpVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "pruebas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pruebas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculos vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_interesado")
    private Interesados interesado;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleados empleado;

    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    // Asigna un valor predeterminado a fechaHoraFin para evitar el error
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin = LocalDateTime.now(); // o LocalDateTime.MIN, según la lógica de negocio

    @Column(name = "comentarios")
    private String comentarios;
}
