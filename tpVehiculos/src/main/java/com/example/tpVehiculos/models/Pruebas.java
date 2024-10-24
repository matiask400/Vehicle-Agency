package com.example.tpVehiculos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;
import java.time.LocalDateTime;

@Entity
@Table(name = "pruebas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pruebas {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
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

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(name = "comentarios")
    private String comentarios;


}