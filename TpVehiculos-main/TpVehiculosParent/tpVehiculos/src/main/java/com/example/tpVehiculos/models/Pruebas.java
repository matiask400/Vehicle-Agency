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

    @Column(name = "fecha_hora_fin", nullable = true)
    private LocalDateTime fechaHoraFin;

    @Column(name = "comentarios")
    private String comentarios;
}
// Se mantienen fechas como string por ISO 801 y no se utilizan LocalDateTime (si hay otra forma hacer)